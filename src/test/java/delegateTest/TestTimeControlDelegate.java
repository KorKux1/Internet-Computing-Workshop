package delegateTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.internetcomputing.workshop.delegate.TsscTimeControlDelegate;
import co.edu.icesi.internetcomputing.workshop.delegate.TsscTimeControlDelegateImp;
import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

@RunWith(MockitoJUnitRunner.class)
public class TestTimeControlDelegate {

	@Mock
	private RestTemplate restTemplate;
	
	private TsscTimeControlDelegate timeControlDelegate;
	
	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		TsscTimeControlDelegateImp timdel = new TsscTimeControlDelegateImp();
		timdel.setRestTemplate(restTemplate);
		timeControlDelegate = timdel;
	}
	
	@Test
	public void addTimecontrolTest() {
		TsscTimecontrol timecontrol = new TsscTimecontrol();
		timecontrol.setName("Goku");
		TransactionBody<TsscTimecontrol> body = new TransactionBody<TsscTimecontrol>();
		body.setBody(timecontrol);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<TransactionBody<TsscTimecontrol>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		timecontrol = timeControlDelegate.addTsscTimeControl(timecontrol);
		assertEquals(timecontrol.getName(), "Goku");
	}
	
	@Test
	public void getTimecontrolTest() {
		TsscTimecontrol timecontrol = new TsscTimecontrol();
		timecontrol.setName("Vegeta");
		TransactionBody<TsscTimecontrol> body = new TransactionBody<TsscTimecontrol>();
		body.setBody(timecontrol);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<TransactionBody<TsscTimecontrol>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		timecontrol = timeControlDelegate.getTsscTimeControl(timecontrol.getId());
		assertEquals(timecontrol.getName(), "Vegeta");
	}
	
	@Test
	public void getAllTimecontrolTest() {
		TransactionBody<List<TsscTimecontrol>> body = new TransactionBody<List<TsscTimecontrol>>();
		List<TsscTimecontrol> list = new ArrayList<TsscTimecontrol>();
		TsscTimecontrol timecontrol1 = new TsscTimecontrol();
		timecontrol1.setName("Goku");
		list.add(timecontrol1);
		TsscTimecontrol timecontrol2 = new TsscTimecontrol();
		timecontrol2.setName("Vegeta");
		list.add(timecontrol2);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<TsscTimecontrol>>> response = new ResponseEntity<TransactionBody<List<TsscTimecontrol>>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<TsscTimecontrol> iterableAdmin = timeControlDelegate.getAllTsscTimeControles();
		int size = 0;
		for (TsscTimecontrol ad : iterableAdmin) {
			size++;
		}
		assertEquals(size, 2);
	}
	
	@Test
	public void updateTimecontrolTest() {
		TsscTimecontrol timeControl = new TsscTimecontrol();
		timeControl.setName("Gojan");
		TransactionBody<TsscTimecontrol> body = new TransactionBody<TsscTimecontrol>();
		body.setBody(timeControl);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<TransactionBody<TsscTimecontrol>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		timeControl.setName("Gohan");
		timeControlDelegate.updateTimeControl(timeControl);
		TsscTimecontrol timeControlN = timeControlDelegate.getTsscTimeControl(timeControl.getId());
		assertEquals("Gohan", timeControlN.getName());
	}
	
}

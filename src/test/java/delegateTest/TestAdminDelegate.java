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

import co.edu.icesi.internetcomputing.workshop.delegate.TsscAdminDelegate;
import co.edu.icesi.internetcomputing.workshop.delegate.TsscAdminDelegateImp;
import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

@RunWith(MockitoJUnitRunner.class)
public class TestAdminDelegate {
	
	@Mock
	private RestTemplate restTemplate;
	
	private TsscAdminDelegate adminDelegate;
	
	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		TsscAdminDelegateImp admdel = new TsscAdminDelegateImp();
		admdel.setRestTemplate(restTemplate);
		adminDelegate = admdel;
	}
	
	@Test
	public void addAdminTest() {
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("Goku");
		TransactionBody<TsscAdmin> body = new TransactionBody<TsscAdmin>();
		body.setBody(admin);
		ResponseEntity<TransactionBody<TsscAdmin>> response = new ResponseEntity<TransactionBody<TsscAdmin>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		admin = adminDelegate.addTsscAdmin(admin);
		assertEquals(admin.getUsername(), "Goku");
	}
	
	@Test
	public void getAdminTest() {
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("Vegeta");
		TransactionBody<TsscAdmin> body = new TransactionBody<TsscAdmin>();
		body.setBody(admin);
		ResponseEntity<TransactionBody<TsscAdmin>> response = new ResponseEntity<TransactionBody<TsscAdmin>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		admin = adminDelegate.getAdmin(admin.getId());
		assertEquals(admin.getUsername(), "Vegeta");
	}
	
	@Test
	public void getAllAdminTest() {
		TransactionBody<List<TsscAdmin>> body = new TransactionBody<List<TsscAdmin>>();
		List<TsscAdmin> list = new ArrayList<TsscAdmin>();
		TsscAdmin admin1 = new TsscAdmin();
		admin1.setUsername("Goku");
		list.add(admin1);
		TsscAdmin admin2 = new TsscAdmin();
		admin2.setUsername("Vegeta");
		list.add(admin2);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<TsscAdmin>>> response = new ResponseEntity<TransactionBody<List<TsscAdmin>>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<TsscAdmin> iterableAdmin = adminDelegate.getAllAdmins();
		int size = 0;
		for (TsscAdmin ad : iterableAdmin) {
			size++;
		}
		assertEquals(size, 2);
	}
	
}

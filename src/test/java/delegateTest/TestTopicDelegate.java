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

import co.edu.icesi.internetcomputing.workshop.delegate.TsscTopicDelegate;
import co.edu.icesi.internetcomputing.workshop.delegate.TsscTopicDelegateImp;
import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
public class TestTopicDelegate {

	@Mock
	private RestTemplate restTemplate;
	
	private TsscTopicDelegate topicDelegate;
	
	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		TsscTopicDelegateImp topdel = new TsscTopicDelegateImp();
		topdel.setRestTemplate(restTemplate);
		topicDelegate = topdel;
	}
	
	@Test
	public void addTopicTest() {
		TsscTopic topic = new TsscTopic();
		topic.setName("Goku");
		TransactionBody<TsscTopic> body = new TransactionBody<TsscTopic>();
		body.setBody(topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		topic = topicDelegate.addTopic(topic);
		assertEquals(topic.getName(), "Goku");
	}
	
	@Test
	public void getTopicTest() {
		TsscTopic topic = new TsscTopic();
		topic.setName("Vegeta");
		TransactionBody<TsscTopic> body = new TransactionBody<TsscTopic>();
		body.setBody(topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		topic = topicDelegate.getTopic(topic.getId());
		assertEquals(topic.getName(), "Vegeta");
	}
	
	@Test
	public void getAllTopicTest() {
		TransactionBody<List<TsscTopic>> body = new TransactionBody<List<TsscTopic>>();
		List<TsscTopic> list = new ArrayList<TsscTopic>();
		TsscTopic topic1 = new TsscTopic();
		topic1.setName("Goku");
		list.add(topic1);
		TsscTopic topic2 = new TsscTopic();
		topic2.setName("Vegeta");
		list.add(topic2);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<TsscTopic>>> response = new ResponseEntity<TransactionBody<List<TsscTopic>>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<TsscTopic> iterableTopic = topicDelegate.getAllTopics();
		int size = 0;
		for (TsscTopic ad : iterableTopic) {
			size++;
		}
		assertEquals(size, 2);
	}
	
	@Test
	public void updateTopicTest() {
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Gojan");
		TransactionBody<TsscTopic> body = new TransactionBody<TsscTopic>();
		body.setBody(topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		topic.setDescription("Gohan");
		topicDelegate.updateTopic(topic);
		TsscTopic topicN = topicDelegate.getTopic(topic.getId());
		assertEquals("Gohan", topicN.getDescription());
	}
	
}

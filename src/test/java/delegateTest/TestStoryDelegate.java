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

import co.edu.icesi.internetcomputing.workshop.delegate.TsscStoryDelegate;
import co.edu.icesi.internetcomputing.workshop.delegate.TsscStoryDelegateImp;
import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

@RunWith(MockitoJUnitRunner.class)
public class TestStoryDelegate {

	@Mock
	private RestTemplate restTemplate;
	
	private TsscStoryDelegate storyDelegate;
	
	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		TsscStoryDelegateImp stodel = new TsscStoryDelegateImp();
		stodel.setRestTemplate(restTemplate);
		storyDelegate = stodel;
	}
	
	@Test
	public void addAdminTest() {
		TsscStory story = new TsscStory();
		story.setDescription("Goku");
		TransactionBody<TsscStory> body = new TransactionBody<TsscStory>();
		body.setBody(story);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		story = storyDelegate.addStory(story);
		assertEquals(story.getDescription(), "Goku");
	}
	
	@Test
	public void getAdminTest() {
		TsscStory story = new TsscStory();
		story.setDescription("Vegeta");
		TransactionBody<TsscStory> body = new TransactionBody<TsscStory>();
		body.setBody(story);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		story = storyDelegate.getStory(story.getId());
		assertEquals(story.getDescription(), "Vegeta");
	}
	
	@Test
	public void getAllAdminTest() {
		TransactionBody<List<TsscStory>> body = new TransactionBody<List<TsscStory>>();
		List<TsscStory> list = new ArrayList<TsscStory>();
		TsscStory story1 = new TsscStory();
		story1.setDescription("Goku");
		list.add(story1);
		TsscStory story2 = new TsscStory();
		story2.setDescription("Vegeta");
		list.add(story2);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<TsscStory>>> response = new ResponseEntity<TransactionBody<List<TsscStory>>>(body, HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<TsscStory> iterableAdmin = storyDelegate.getAllStories();
		int size = 0;
		for (TsscStory ad : iterableAdmin) {
			size++;
		}
		assertEquals(size, 2);
	}
	
}

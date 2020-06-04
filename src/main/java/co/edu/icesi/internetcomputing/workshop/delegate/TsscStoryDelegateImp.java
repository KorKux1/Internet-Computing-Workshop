package co.edu.icesi.internetcomputing.workshop.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

@Component
public class TsscStoryDelegateImp extends GenericDelegate implements TsscStoryDelegate {
	
	public TsscStoryDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<TsscStory> getAllStories() {
		TransactionBody<List<TsscStory>> transaction = new TransactionBody<>("storiesList", new ArrayList<>());
		HttpEntity<TransactionBody<List<TsscStory>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<TsscStory>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/stories", HttpMethod.GET, 
					request, new ParameterizedTypeReference<TransactionBody<List<TsscStory>>>() {
					});
		
		Iterable<TsscStory> it = response.getBody().getBody();
		return it;
	}

	@Override
	public TsscStory addStory(TsscStory story) {
		TransactionBody<TsscStory> transaction = new TransactionBody<>("newStory", story);
		HttpEntity<TransactionBody<TsscStory>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscStory>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/stories",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<TsscStory>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
		return story;
	}

	@Override
	public TsscStory getStory(Long id) {
		TransactionBody<Long> transaction = new TransactionBody<>("storyid", id);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscStory>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/stories/" + id ,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<TsscStory>>() {
		});
		return response.getBody().getBody();
	}

}

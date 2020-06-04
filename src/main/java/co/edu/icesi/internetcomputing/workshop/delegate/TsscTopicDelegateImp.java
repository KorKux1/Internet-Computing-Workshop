package co.edu.icesi.internetcomputing.workshop.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public class TsscTopicDelegateImp extends GenericDelegate implements TsscTopicDelegate{

	public TsscTopicDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<TsscTopic> getAllTopics() {
		TransactionBody<List<TsscTopic>> transaction = new TransactionBody<>("topicList", new ArrayList<>());
		HttpEntity<TransactionBody<List<TsscTopic>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<TsscTopic>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/topics", HttpMethod.GET, 
					request, new ParameterizedTypeReference<TransactionBody<List<TsscTopic>>>() {
					});
		
		Iterable<TsscTopic> it = response.getBody().getBody();
		return it;
	}

	@Override
	public TsscTopic addTopic(TsscTopic topic) {
		TransactionBody<TsscTopic> transaction = new TransactionBody<>("newTopic", topic);
		HttpEntity<TransactionBody<TsscTopic>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscTopic>> response = null;
		
        response =restTemplate.exchange(SERVER+"api/topics", HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<TsscTopic>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
		return topic;
	}

	@Override
	public TsscTopic getTopic(Long id) {
		TransactionBody<Long> transaction = new TransactionBody<>("topicId", id);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscTopic>> response = null;
		
		response =	restTemplate.exchange(SERVER+"api/topics/" + id,HttpMethod.GET, request,
        		  	new ParameterizedTypeReference<TransactionBody<TsscTopic>>() {
					});
		return response.getBody().getBody();
	}
}

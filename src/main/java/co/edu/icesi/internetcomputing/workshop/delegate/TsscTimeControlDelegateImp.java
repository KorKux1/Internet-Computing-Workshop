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
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

@Component
public class TsscTimeControlDelegateImp extends GenericDelegate implements TsscTimeControlDelegate {

	public TsscTimeControlDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<TsscTimecontrol> getAllTsscTimeControles() {
		TransactionBody<List<TsscTimecontrol>> transaction = new TransactionBody<>("timeControlList", new ArrayList<>());
		HttpEntity<TransactionBody<List<TsscTimecontrol>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<TsscTimecontrol>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/timeControles", HttpMethod.GET, 
					request, new ParameterizedTypeReference<TransactionBody<List<TsscTimecontrol>>>() {
					});
		
		Iterable<TsscTimecontrol> it = response.getBody().getBody();
		return it;
	}

	@Override
	public TsscTimecontrol addTsscTimeControl(TsscTimecontrol timeControl) {
		TransactionBody<TsscTimecontrol> transaction = new TransactionBody<>("newTimeControl", timeControl);
		HttpEntity<TransactionBody<TsscTimecontrol>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/timeControles", HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<TsscTimecontrol>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
		return timeControl;
	}

	@Override
	public TsscTimecontrol getTsscTimeControl(Long id) {
		TransactionBody<Long> transaction = new TransactionBody<>("timeControlId", id);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/timeControles/" + id, HttpMethod.GET, request,
        			new ParameterizedTypeReference<TransactionBody<TsscTimecontrol>>() {
					});
		return response.getBody().getBody();
	}

}

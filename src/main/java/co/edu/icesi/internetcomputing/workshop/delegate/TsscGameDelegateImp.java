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
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

@Component
public class TsscGameDelegateImp extends GenericDelegate implements TsscGameDelegate{

	public TsscGameDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<TsscGame> getAllGames() {
		TransactionBody<List<TsscGame>> transaction = new TransactionBody<>("gamesList", new ArrayList<>());
		HttpEntity<TransactionBody<List<TsscGame>>> request = new HttpEntity<> (transaction);
		ResponseEntity<TransactionBody<List<TsscGame>>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/games", HttpMethod.GET, 
				request, new ParameterizedTypeReference<TransactionBody<List<TsscGame>>>() {
				});
		
		Iterable<TsscGame> it = response.getBody().getBody();
		return it;
	}

	@Override
	public TsscGame addGame(TsscGame game) {
		TransactionBody<TsscGame> transaction = new TransactionBody<>("newGame", game);
		HttpEntity<TransactionBody<TsscGame>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscGame>> response = null;
		
		
        response =restTemplate.exchange(SERVER+"api/games",HttpMethod.POST, request,
        		new ParameterizedTypeReference<TransactionBody<TsscGame>>() {
		});
        
        if (response.getStatusCode() == HttpStatus.MULTI_STATUS) {
        	throw new RuntimeException(response.getBody().getApiContext());
        }
        
		return game;
	}

	@Override
	public TsscGame getGame(Long id) {
		TransactionBody<Long> transaction = new TransactionBody<>("id", id);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscGame>> response = null;
		
		response =restTemplate.exchange(SERVER+"api/games/" + id,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<TsscGame>>() {
		});
		return response.getBody().getBody();
	}

	@Override
	public void removeGame(TsscGame game) throws Exception {
		TransactionBody<TsscGame> transaction = new TransactionBody<>("delGame", game);
		HttpEntity<TransactionBody<TsscGame>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscGame>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/games", HttpMethod.DELETE, request,
				new ParameterizedTypeReference<TransactionBody<TsscGame>>() {
				});
		if(response.getStatusCode().equals(HttpStatus.PRECONDITION_FAILED)) {
			Exception e =new Exception("Error Eliminando");
			throw e;
		}
	}

	@Override
	public void updateGame(TsscGame game) {
		TransactionBody<TsscGame> transaction = new TransactionBody<>("uptGame", game);
		HttpEntity<TransactionBody<TsscGame>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscGame>> response = null;
		response = restTemplate.exchange(SERVER+"api/games", HttpMethod.PUT, request,
				new ParameterizedTypeReference<TransactionBody<TsscGame>>() {
		});
	}
}

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
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

@Component
public class TsscAdminDelegateImp extends GenericDelegate implements TsscAdminDelegate {

	
	public TsscAdminDelegateImp() {
		super();
	}
	
	@Override
	public Iterable<TsscAdmin> getAllAdmins() {
		TransactionBody<List<TsscAdmin>> transaction = new TransactionBody<>("adminList", new ArrayList<>());
		HttpEntity<TransactionBody<List<TsscAdmin>>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<List<TsscAdmin>>> response = null;
		response = restTemplate.exchange(SERVER+"api/admins", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<List<TsscAdmin>>>() {
		});
		Iterable<TsscAdmin> iterableAdmin = response.getBody().getBody();
		return iterableAdmin;
	}
	
	@Override
	public TsscAdmin addTsscAdmin(TsscAdmin admin) {
		TransactionBody<TsscAdmin> transaction = new TransactionBody<>("newAdmin", admin);
		HttpEntity<TransactionBody<TsscAdmin>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscAdmin>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/admins", HttpMethod.POST, request, new ParameterizedTypeReference<TransactionBody<TsscAdmin>>() {
		});
		return admin;
	}


	@Override
	public TsscAdmin getAdmin(Long id) {
		TransactionBody<Long> transaction = new TransactionBody<>("adminid", id);
		HttpEntity<TransactionBody<Long>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscAdmin>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/admins/" + id,HttpMethod.GET, request,
        		new ParameterizedTypeReference<TransactionBody<TsscAdmin>>() {
		});
		return response.getBody().getBody();

	}

	@Override
	public void removeAdmin(TsscAdmin admin) throws Exception {
		TransactionBody<TsscAdmin> transaction = new TransactionBody<>("delAdmin", admin);
		HttpEntity<TransactionBody<TsscAdmin>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscAdmin>> response = null;
		
		response = restTemplate.exchange(SERVER+"api/admins", HttpMethod.DELETE, request,
				new ParameterizedTypeReference<TransactionBody<TsscAdmin>>() {
				});
		if(response.getStatusCode().equals(HttpStatus.PRECONDITION_FAILED)) {
			Exception e =new Exception("Error Eliminando");
			throw e;
		}
		
	}

	@Override
	public void updateAdmin(TsscAdmin admin) {
		TransactionBody<TsscAdmin> transaction = new TransactionBody<>("uptAdmin", admin);
		HttpEntity<TransactionBody<TsscAdmin>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscAdmin>> response = null;
		response = restTemplate.exchange(SERVER+"api/admins", HttpMethod.PUT, request,
				new ParameterizedTypeReference<TransactionBody<TsscAdmin>>() {
		});
	}

	
}

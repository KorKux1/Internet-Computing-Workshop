package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

public interface IApiAdmin {
	
	public TransactionBody<Iterable<TsscAdmin>> getAdmins();
	
	public ResponseEntity<TransactionBody<TsscAdmin>> addAdmin(TransactionBody<TsscAdmin> bus);
	
	public ResponseEntity<TransactionBody<TsscAdmin>> getAdmin(Long id);
}

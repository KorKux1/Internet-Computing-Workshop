package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

public interface IApiAdmin {
	
	public TransactionBody<Iterable<TsscAdmin>> getAdmins();
	
	public ResponseEntity<TransactionBody<TsscAdmin>> addAdmin(TransactionBody<TsscAdmin> admin);
	
	public ResponseEntity<TransactionBody<TsscAdmin>> getAdmin(Long id);
	
	public ResponseEntity<TransactionBody<TsscAdmin>> updateAdmin(TransactionBody<TsscAdmin> admin);
	
	public ResponseEntity<TransactionBody<TsscAdmin>> deleteAdmin(@RequestBody TransactionBody<TsscAdmin> admin);
}

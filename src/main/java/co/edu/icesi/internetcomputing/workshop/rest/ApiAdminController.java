package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;
import co.edu.icesi.internetcomputing.workshop.services.TsscAdminService;

@RestController
public class ApiAdminController implements IApiAdmin{
	
	@Autowired
	TsscAdminService tsscAdminService;
	

	@Override
	@GetMapping("/api/admins")
	public TransactionBody<Iterable<TsscAdmin>> getAdmins() {
		TransactionBody<Iterable<TsscAdmin>> tb= new TransactionBody<>();
		tb.setBody(tsscAdminService.getAllAdmins());
		return tb;
	}

	@Override
	public ResponseEntity<TransactionBody<TsscAdmin>> addAdmin(TransactionBody<TsscAdmin> bus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<TransactionBody<TsscAdmin>> getAdmin(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}

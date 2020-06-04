package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("/api/admins")
	public ResponseEntity<TransactionBody<TsscAdmin>> addAdmin(@RequestBody TransactionBody<TsscAdmin> admin) {
		TsscAdmin tsscAdmin = admin.getBody();
		tsscAdminService.save(tsscAdmin);
		TransactionBody<TsscAdmin> tb = new TransactionBody<TsscAdmin>("newAdmin", tsscAdmin);
		ResponseEntity<TransactionBody<TsscAdmin>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@GetMapping("/api/admins/{id}")
	public ResponseEntity<TransactionBody<TsscAdmin>> getAdmin(@PathVariable Long id) {
		TsscAdmin tsscAdmin = tsscAdminService.findById(id);
		TransactionBody<TsscAdmin> tb = new TransactionBody<>("NewBus", tsscAdmin);
		ResponseEntity<TransactionBody<TsscAdmin>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}
}

package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;
import co.edu.icesi.internetcomputing.workshop.services.TsscTimeControlService;

@RestController
public class ApiTimeController implements IApiTimeControl {
	
	@Autowired
	private TsscTimeControlService tsscTimeControlService;

	@Override
	@GetMapping("/api/timeControles")
	public TransactionBody<Iterable<TsscTimecontrol>> getTimeControl() {
		TransactionBody<Iterable<TsscTimecontrol>> tb= new TransactionBody<>();
		tb.setBody(tsscTimeControlService.findAll());
		return tb;
	}

	@Override
	@PostMapping("/api/timeControles")
	public ResponseEntity<TransactionBody<TsscTimecontrol>> addTimeControl(
			@RequestBody TransactionBody<TsscTimecontrol> timeControl) {
		TsscTimecontrol timeC = timeControl.getBody();
		tsscTimeControlService.save(timeC);
		TransactionBody<TsscTimecontrol> tb = new TransactionBody<TsscTimecontrol>("NewTimeControl", timeC);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@GetMapping("/api/timeControles/{id}")
	public ResponseEntity<TransactionBody<TsscTimecontrol>> getTimeControl(@PathVariable Long id) {
		TsscTimecontrol timeC = tsscTimeControlService.findById(id);
		TransactionBody<TsscTimecontrol> tb = new TransactionBody<>("getTimeControl", timeC);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@PutMapping("/api/timeControles")
	public ResponseEntity<TransactionBody<TsscTimecontrol>> updateTimeControl(
			@RequestBody TransactionBody<TsscTimecontrol> timeControl) {
		TsscTimecontrol timeC = timeControl.getBody();
		tsscTimeControlService.update(timeC);
		TransactionBody<TsscTimecontrol> tb = new TransactionBody<>("uptTimeControl", timeC);
		ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@DeleteMapping("/api/timeControles")
	public ResponseEntity<TransactionBody<TsscTimecontrol>> deleteTimeControl(
			TransactionBody<TsscTimecontrol> timeControl) {
		
		TsscTimecontrol timec = timeControl.getBody();
		
		try {
			tsscTimeControlService.remove(timec);
			TransactionBody<TsscTimecontrol> tb = new TransactionBody<>("DelTimeControl",timec);
			ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
		}catch(Exception e) {
			TransactionBody<TsscTimecontrol> tb = new TransactionBody<>("DelTimeControl",timec);
			ResponseEntity<TransactionBody<TsscTimecontrol>> response = new ResponseEntity<> (tb,
					HttpStatus.PRECONDITION_FAILED);
			return response;
		}
	}
	

}

package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

public interface IApiTimeControl {
	
	public TransactionBody<Iterable<TsscTimecontrol>> getTimeControl();
	
	public ResponseEntity<TransactionBody<TsscTimecontrol>> addTimeControl(TransactionBody<TsscTimecontrol> timeControl);
	
	public ResponseEntity<TransactionBody<TsscTimecontrol>> getTimeControl(Long id);
	
	public ResponseEntity<TransactionBody<TsscTimecontrol>> updateTimeControl(TransactionBody<TsscTimecontrol> timeControl);
	
	public ResponseEntity<TransactionBody<TsscTimecontrol>> deleteTimeControl(@RequestBody TransactionBody<TsscTimecontrol> timeControl);

}

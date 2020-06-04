package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public interface IApiTopic {
	
public TransactionBody<Iterable<TsscTopic>> getSitios();
	
	public ResponseEntity<TransactionBody<TsscTopic>> addTsscTopic(TransactionBody<TsscTopic> topic);
	
	public ResponseEntity<TransactionBody<TsscTopic>> getTsscTopic(Long id);
	
	public ResponseEntity<TransactionBody<TsscTopic>> updateTsscTopic(TransactionBody<TsscTopic> topic);
	
	public ResponseEntity<TransactionBody<TsscTopic>> deleteTsscTopic(@RequestBody TransactionBody<TsscTopic> topic);
}

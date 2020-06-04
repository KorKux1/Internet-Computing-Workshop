package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

public interface IApiStory {
	
	public TransactionBody<Iterable<TsscStory>> getStories();
	
	public ResponseEntity<TransactionBody<TsscStory>> addTsscStory(TransactionBody<TsscStory> story);
	
	public ResponseEntity<TransactionBody<TsscStory>> getStory(Long id);
	
	public ResponseEntity<TransactionBody<TsscStory>> updateStory(TransactionBody<TsscStory> story);
	
	public ResponseEntity<TransactionBody<TsscStory>> deleteStory(@RequestBody TransactionBody<TsscStory> story);

}

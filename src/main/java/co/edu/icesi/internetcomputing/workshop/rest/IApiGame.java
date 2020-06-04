package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

public interface IApiGame {
	
	public TransactionBody<Iterable<TsscGame>> getGames();
	
	public ResponseEntity<TransactionBody<TsscGame>> addTsscGame(TransactionBody<TsscGame> game);
	
	public ResponseEntity<TransactionBody<TsscGame>> getGame(Long id);
	
	public ResponseEntity<TransactionBody<TsscGame>> updateGame(TransactionBody<TsscGame> game);
	
	public ResponseEntity<TransactionBody<TsscGame>> deleteGame(@RequestBody TransactionBody<TsscGame> game);

}

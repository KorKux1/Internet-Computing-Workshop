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
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameService;

@RestController
public class ApiGameController implements IApiGame {
	
	@Autowired
	private TsscGameService tsscGameService;

	@Override
	@GetMapping("/api/games")
	public TransactionBody<Iterable<TsscGame>> getGames() {
		TransactionBody<Iterable<TsscGame>> tb = new TransactionBody<>();
		tb.setBody(tsscGameService.findAll());	
		return tb;
	}

	@Override
	@PostMapping("/api/games")
	public ResponseEntity<TransactionBody<TsscGame>> addTsscGame(@RequestBody TransactionBody<TsscGame> game) {
		TsscGame tsscGame = game.getBody();
		tsscGameService.save(tsscGame);
		TransactionBody<TsscGame> tb = new TransactionBody<TsscGame>("NewGame", tsscGame);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@GetMapping("/api/games/{id}")
	public ResponseEntity<TransactionBody<TsscGame>> getGame(@PathVariable Long id) {
		TsscGame game = tsscGameService.findById(id);
		TransactionBody<TsscGame> tb = new TransactionBody<>("getGame", game);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@PutMapping("/api/games")
	public ResponseEntity<TransactionBody<TsscGame>> updateGame(@RequestBody TransactionBody<TsscGame> game) {
		TsscGame tsscGame = game.getBody();
		tsscGameService.update(tsscGame);
		TransactionBody<TsscGame> tb = new TransactionBody<>("uptGame", tsscGame);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<>(tb, HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@DeleteMapping("/api/games")
	public ResponseEntity<TransactionBody<TsscGame>> deleteGame(TransactionBody<TsscGame> game) {
		TsscGame tsscGame = game.getBody();
		try {
			tsscGameService.remove(tsscGame);
			TransactionBody<TsscGame> tb = new TransactionBody<>("DelGame", tsscGame);
			ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
		}catch(Exception e) {
			TransactionBody<TsscGame> tb = new TransactionBody<>("DelServ", tsscGame);
			ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<> (tb,
					HttpStatus.PRECONDITION_FAILED);
			return response;
		}
	}
}

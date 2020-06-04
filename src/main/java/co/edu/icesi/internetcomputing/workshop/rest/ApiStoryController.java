package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryService;

@RestController
public class ApiStoryController implements IApiStory {
	
	@Autowired
	private TsscStoryService tsscStoryService;

	@Override
	@GetMapping("/api/stories")
	public TransactionBody<Iterable<TsscStory>> getStories() {
		TransactionBody<Iterable<TsscStory>> tb= new TransactionBody<>();
		tb.setBody(tsscStoryService.findAll());	
		return tb;
	}

	@Override
	@PostMapping("/api/stories")
	public ResponseEntity<TransactionBody<TsscStory>> addTsscStory(@RequestBody TransactionBody<TsscStory> story) {
		TsscStory tsscStory = story.getBody();
		tsscStoryService.save(tsscStory);
		TransactionBody<TsscStory> tb = new TransactionBody<TsscStory>("NewStory",tsscStory);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@GetMapping("/api/stories/{id}")
	public ResponseEntity<TransactionBody<TsscStory>> getStory(Long id) {
		TsscStory story = tsscStoryService.findById(id);
		TransactionBody<TsscStory> tb = new TransactionBody<>("getSitio",story);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@PutMapping("/api/stories")
	public ResponseEntity<TransactionBody<TsscStory>> updateStory(TransactionBody<TsscStory> story) {
		TsscStory tsscStory =story.getBody();
		tsscStoryService.save(tsscStory);
		TransactionBody<TsscStory> tb = new TransactionBody<>("uptSitio",tsscStory);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@DeleteMapping("/api/stories")
	public ResponseEntity<TransactionBody<TsscStory>> deleteStory(TransactionBody<TsscStory> story) {
		TsscStory tsscStory = story.getBody();
		try {
			tsscStoryService.remove(tsscStory);
			TransactionBody<TsscStory> tb = new TransactionBody<>("DelStory", tsscStory);
			ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<> (tb,
					HttpStatus.ACCEPTED);
			return response;
		}catch(Exception e) {
			TransactionBody<TsscStory> tb = new TransactionBody<>("DelStory", tsscStory);
			ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<> (tb,
					HttpStatus.PRECONDITION_FAILED);
			return response;
		}
	}


}

package co.edu.icesi.internetcomputing.workshop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import co.edu.icesi.internetcomputing.workshop.model.TransactionBody;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicService;

public class ApiTopicController implements IApiTopic {
	
	@Autowired
	private TsscTopicService tsscTopicService;

	@Override
	@GetMapping("/api/topics")
	public TransactionBody<Iterable<TsscTopic>> getSitios() {
		TransactionBody<Iterable<TsscTopic>> tb= new TransactionBody<>();
		tb.setBody(tsscTopicService.findAll());
		
		return tb;
	}

	@Override
	@PostMapping("/api/topics")
	public ResponseEntity<TransactionBody<TsscTopic>> addTsscTopic(TransactionBody<TsscTopic> topic) {
		TsscTopic tsscTopic = topic.getBody();
		tsscTopicService.save(tsscTopic);
		TransactionBody<TsscTopic> tb = new TransactionBody<TsscTopic>("NewTopic",tsscTopic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@GetMapping("/api/topics/{id}")
	public ResponseEntity<TransactionBody<TsscTopic>> getTsscTopic(Long id) {
		
		TsscTopic topic = tsscTopicService.findById(id);
		TransactionBody<TsscTopic> tb = new TransactionBody<>("getTopic", topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<>(tb,
				HttpStatus.ACCEPTED);
		return response;
	}

	@Override
	@PutMapping("/api/topics")
	public ResponseEntity<TransactionBody<TsscTopic>> updateTsscTopic(TransactionBody<TsscTopic> topic) {
		
		return null;
	}

	@Override
	public ResponseEntity<TransactionBody<TsscTopic>> deleteTsscTopic(TransactionBody<TsscTopic> topic) {
		
		return null;
	}

}

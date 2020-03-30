package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public interface TsscTopicService {
	public boolean save(TsscTopic tsscTopic);
	
	public void removeAll();
	
	public Optional<TsscTopic> findById(long id);
	
	public Iterable<TsscTopic> findAll();
}

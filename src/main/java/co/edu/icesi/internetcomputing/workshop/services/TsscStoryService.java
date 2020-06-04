package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

public interface TsscStoryService {
	
	public boolean save(TsscStory tsscStory);
	public TsscStory findById(long id);
	public Iterable<TsscStory> findAll();
	
	public void remove(TsscStory tsscStory);

}

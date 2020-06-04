package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

public interface ITsscStoryDao {
	public void save(TsscStory entity);
	
	public void update(TsscStory entity);
		
	public void delete(TsscStory entity);
		
	public TsscStory findById(Long id);
		
	public List<TsscStory> findAll();
}

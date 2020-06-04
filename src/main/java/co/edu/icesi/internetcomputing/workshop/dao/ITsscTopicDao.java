package co.edu.icesi.internetcomputing.workshop.dao;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.internetcomputing.workshop.dtos.TopicWithGames;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

public interface ITsscTopicDao {
	public void save(TsscTopic entity);
	
	public void update(TsscTopic entity);
	
	public void delete(TsscTopic entity);
	
	public TsscTopic findById(long id);
	
	public List<TsscTopic> findByName(String name);
	
	public List<TsscTopic> findByDescription(String description);
	
	public List<TsscTopic> findAll();
	
	public List<TopicWithGames> findTopicWithGamesByDate(LocalDate date);
	
}

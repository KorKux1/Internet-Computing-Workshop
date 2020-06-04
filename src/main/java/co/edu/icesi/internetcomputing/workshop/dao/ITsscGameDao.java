package co.edu.icesi.internetcomputing.workshop.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

public interface ITsscGameDao {
	
	public void save(TsscGame entity);
	
	public void update(TsscGame entity);
	
	public void delete(TsscGame entity);
	
	public TsscGame findById(long id);
	
	public List<TsscGame> findAll();
	
	public List<TsscGame> findByName(String name);
	
	public List<TsscGame> findByDescription(String description);
	
	public List<TsscGame> findByTopicId(long TopicId);
	
	public List<TsscGame> findByRangeDates(LocalDate startDate, LocalDate endDate);
	
	public List<TsscGame> findByDateHours(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);
	
	public List<TsscGame> findByCountStoryOrNotTimeGame(LocalDate date);
	

}

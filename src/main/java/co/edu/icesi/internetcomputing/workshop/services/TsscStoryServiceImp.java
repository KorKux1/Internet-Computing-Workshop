package co.edu.icesi.internetcomputing.workshop.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.dao.TsscGameDao;
import co.edu.icesi.internetcomputing.workshop.dao.TsscStoryDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

@Service
public class TsscStoryServiceImp implements TsscStoryService {

	private TsscStoryDao tsscStoryDao;
	private TsscGameDao tsscGameDao;
	
	@Autowired
	public TsscStoryServiceImp(TsscStoryDao tsscStoryDao, TsscGameDao tsscGameDao) {
		this.tsscStoryDao = tsscStoryDao;
		this.tsscGameDao = tsscGameDao;
	}
	
	@Override
	public boolean save(TsscStory tsscStory) {
		BigDecimal zero = new BigDecimal(0);
		if(tsscStory == null) {
			throw new NullPointerException("La historia no puede ser nula");
		}
		if (!(tsscStory.getPriority().compareTo(zero) > 0) || !(tsscStory.getInitialSprint().compareTo(zero) >0) || !(tsscStory.getBusinessValue().compareTo(zero) > 0)){
			return false;
		}
		if(tsscStory.getTsscGame() == null) {
			return false;
		}
		else {
			TsscGame tsscGames = tsscGameDao.findById(tsscStory.getTsscGame().getId());
			if(tsscGames == null) {
				return false;
			}
		}
		tsscStoryDao.save(tsscStory);
		return true;
	}
	
	public boolean editStory(TsscStory story, Long id) throws Exception {
		if (story != null && story.getBusinessValue().compareTo(new BigDecimal(0)) == 1 && story.getInitialSprint().compareTo(new BigDecimal(0)) == 1 && story.getPriority().compareTo(new BigDecimal(0)) == 1) {
			tsscStoryDao.update(story);
			return true;
		} else {
			throw new Exception();
		}
	}
	
	@Override
	public TsscStory findById(long id) {
		return tsscStoryDao.findById(id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return tsscStoryDao.findAll();
	}
	
	public void delete(TsscStory story) {
		tsscStoryDao.delete(story);
	}

}

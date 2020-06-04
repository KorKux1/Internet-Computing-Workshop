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

	@Override
	public TsscStory findById(long id) {
		return tsscStoryDao.findById(id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return tsscStoryDao.findAll();
	}

	@Override
	public void remove(TsscStory tsscStory) {
		tsscStoryDao.delete(tsscStory);	
	}

}

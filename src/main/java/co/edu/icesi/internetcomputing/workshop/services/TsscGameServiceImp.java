package co.edu.icesi.internetcomputing.workshop.services;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.dao.TsscGameDao;
import co.edu.icesi.internetcomputing.workshop.dao.TsscStoryDao;
import co.edu.icesi.internetcomputing.workshop.dao.TsscTopicDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;

@Service
public class TsscGameServiceImp implements TsscGameService {

	private TsscGameDao tsscGameDao;
	
	private TsscTopicDao tsscTopicDao;
	
	private TsscStoryDao tsscStoryDao;
	
	
	@Autowired
	public TsscGameServiceImp(TsscGameDao tsscGameDao, TsscTopicDao tsscTopicDao, TsscStoryDao tsscStoryDao) {
		this.tsscGameDao = tsscGameDao;
		this.tsscTopicDao = tsscTopicDao;
		this.tsscStoryDao = tsscStoryDao;
	}
	
	@Override
	public boolean save(TsscGame tsscGame) {
		if(tsscGame == null) {
			throw new NullPointerException("El Game no puede ser nulo");
		}
		if (!(tsscGame.getNGroups() > 0) || !(tsscGame.getNSprints() > 0) ) {
			return false;
		}
		if (tsscGame.getTsscTopic() != null) {
			TsscTopic aux = tsscTopicDao.findById(tsscGame.getTsscTopic().getId());
			if (aux == null) {
				return false;
			}
		}
		tsscGameDao.save(tsscGame);
		return true;
	}

	@Override
	public TsscGame findById(long id) {
		return tsscGameDao.findById(id);
	}

	@Override
	@Transactional
	public boolean save2(TsscGame tsscGame) {
		if(tsscGame == null) {
			throw new NullPointerException("El Game no puede ser nulo");
		}
		if (!(tsscGame.getNGroups() > 0) || !(tsscGame.getNSprints() > 0) ) {
			return false;
		}
		if (tsscGame.getTsscTopic() != null) {
			TsscTopic aux = tsscTopicDao.findById(tsscGame.getTsscTopic().getId());
			if (aux == null) {
				return false;
			}else {
				TsscTopic tsscTopic = tsscGame.getTsscTopic(); //aux.get();
				for(TsscStory tsscStory: tsscTopic.getTsscStories()) {
					TsscStory target = new TsscStory();
					BeanUtils.copyProperties(tsscStory, target);
					tsscStoryDao.save(target);
					tsscGame.addTsscStory(target);
				}
			}
		}
		tsscGameDao.save(tsscGame);
		return true;
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return tsscGameDao.findAll();
	}

	@Override
	public void remove(TsscGame tsscGame) {
		tsscGameDao.delete(tsscGame);
	}

	@Override
	public void update(TsscGame tsscGame) {
		tsscGameDao.update(tsscGame);		
	}
	
}

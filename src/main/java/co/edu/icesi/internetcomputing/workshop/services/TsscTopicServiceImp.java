package co.edu.icesi.internetcomputing.workshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.dao.TsscTopicDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@Service
public class TsscTopicServiceImp implements TsscTopicService {
	
	private TsscTopicDao tsscTopicDao;
	
	@Autowired
	public TsscTopicServiceImp(TsscTopicDao tsscTopicDao) {
		this.tsscTopicDao = tsscTopicDao;
	}

	@Override
	@Transactional
	public boolean save(TsscTopic tsscTopic) {
		
		if (tsscTopic == null) {
			throw new NullPointerException("El topic no puede ser Nulo");
		}
		
		if(!(tsscTopic.getDefaultGroups() > 0) || !(tsscTopic.getDefaultSprints() >0)){
			return false;
		}	
		
		tsscTopicDao.save(tsscTopic);		
		return true;
	}

	@Override
	public void removeAll() {
	//	tsscTopicDato.deleteAll();
	}

	@Override
	public TsscTopic findById(long id) {
		return tsscTopicDao.findById(id);
	}
	
	@Override
	public Iterable<TsscTopic> findAll() {
		return tsscTopicDao.findAll();
	}
	
}

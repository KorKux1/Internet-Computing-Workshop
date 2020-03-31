package co.edu.icesi.internetcomputing.workshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;

@Service
public class TsscTopicServiceImp implements TsscTopicService {
	
	private TsscTopicRepository tsscTopicRepository;
	
	@Autowired
	public TsscTopicServiceImp(TsscTopicRepository tsscTopicRepository) {
		this.tsscTopicRepository = tsscTopicRepository;
	}

	@Override
	public boolean save(TsscTopic tsscTopic) {
		
		if (tsscTopic == null) {
			throw new NullPointerException("El topic no puede ser Nulo");
		}
		
		if(!(tsscTopic.getDefaultGroups() > 0) || !(tsscTopic.getDefaultSprints() >0)){
			return false;
		}	
		
		tsscTopicRepository.save(tsscTopic);		
		return true;
	}

	@Override
	public void removeAll() {
		tsscTopicRepository.deleteAll();
	}

	@Override
	public TsscTopic findById(long id) {
		return tsscTopicRepository.findById(id).get();
	}
	
	@Override
	public Iterable<TsscTopic> findAll() {
		return tsscTopicRepository.findAll();
	}
	
	
	
}

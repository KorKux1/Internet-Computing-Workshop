package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;

@Service
public class TsscGameServiceImp implements TsscGameService {

	private TsscGameRepository tsscGameRepository;
	
	private TsscTopicRepository tsscTopicRepository;
	
	private TsscStoryRepository tsscStoryRepository;
	
	
	@Autowired
	public TsscGameServiceImp(TsscGameRepository tsscGameRepository,TsscTopicRepository tsscTopicRepository, TsscStoryRepository tsscStoryRepository) {
		this.tsscGameRepository = tsscGameRepository;
		this.tsscTopicRepository = tsscTopicRepository;
		this.tsscStoryRepository = tsscStoryRepository;
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
			Optional<TsscTopic> aux = tsscTopicRepository.findById(tsscGame.getTsscTopic().getId());
			if (aux == null) {
				return false;
			}
		}
		tsscGameRepository.save(tsscGame);
		return true;
	}

	@Override
	public Optional<TsscGame> findById(long id) {
		return tsscGameRepository.findById(id);
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
			Optional<TsscTopic> aux = tsscTopicRepository.findById(tsscGame.getTsscTopic().getId());
			if (aux == null) {
				return false;
			}else {
				TsscTopic tsscTopic = tsscGame.getTsscTopic(); //aux.get();
				for(TsscStory tsscStory: tsscTopic.getTsscStories()) {
					TsscStory target = new TsscStory();
					BeanUtils.copyProperties(tsscStory, target);
					tsscStoryRepository.save(target);
					tsscGame.addTsscStory(target);
				}
			}
		}
		tsscGameRepository.save(tsscGame);
		return true;
	}
	
}

package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;


public class TsscGameServiceImp implements TsscGameService {

	private TsscGameRepository tsscGameRepository;
	private TsscTopicRepository tsscTopicRepository;
	
	
	@Autowired
	public TsscGameServiceImp(TsscGameRepository tsscGameRepository,TsscTopicRepository tsscTopicRepository) {
		this.tsscGameRepository = tsscGameRepository;
		this.tsscTopicRepository = tsscTopicRepository;
	}
	
	@Override
	public boolean save(TsscGame tsscGame) {
		if(tsscGame == null) {
			throw new NullPointerException("El Game no puede ser nulo");
		}
		if (!(tsscGame.getTsscGroups().size() > 0) || !(tsscGame.getTsscSprints().size() > 0) ) {
			return false;
		}
		if (tsscGame.getTsscTopic() != null) {
			TsscTopic aux = tsscTopicRepository.findById(tsscGame.getId()).get();
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
	
}

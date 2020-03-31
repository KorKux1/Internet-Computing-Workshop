package co.edu.icesi.internetcomputing.workshop.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;

public class TsscStoryServiceImp implements TsscStoryService {

	private TsscStoryRepository tsscStoryRepository;
	private TsscGameRepository tsscGameRepository;
	
	@Autowired
	public TsscStoryServiceImp(TsscStoryRepository tsscStoryRepository, TsscGameRepository tsscGameRepository) {
		this.tsscStoryRepository = tsscStoryRepository;
		this.tsscGameRepository = tsscGameRepository;
	}
	
	@Override
	public boolean save(TsscStory tsscStory) {
		BigDecimal zero = new BigDecimal(0);
		if(tsscStory == null) {
			throw new NullPointerException("La historia no puede ser nula");
		}
		int aux = tsscStory.getPriority().compareTo(zero);
		if (!(tsscStory.getPriority().compareTo(zero) > 0 && tsscStory.getInitialSprint().compareTo(zero) >0 && tsscStory.getBusinessValue().compareTo(zero) > 0)){
			return false;
		}
		if(tsscStory.getTsscGame() == null) {
			return false;
		}
		else {
			Optional<TsscGame> tsscGames = tsscGameRepository.findById(tsscStory.getTsscGame().getId());
			if(tsscGames == null) {
				return false;
			}
		}
		tsscStoryRepository.save(tsscStory);
		return true;
	}

}

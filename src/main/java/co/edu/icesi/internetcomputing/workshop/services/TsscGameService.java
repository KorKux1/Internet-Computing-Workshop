package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

public interface TsscGameService {
	
	public Optional<TsscGame> findById(long id);

	public boolean save(TsscGame tsscGame);
}

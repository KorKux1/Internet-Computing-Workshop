package co.edu.icesi.internetcomputing.workshop.services;

import java.util.Optional;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

public interface TsscGameService {
	
	public TsscGame findById(long id);
	
	public Iterable<TsscGame> findAll();

	public boolean save(TsscGame tsscGame);
	
	public boolean save2(TsscGame tsscGame);
}

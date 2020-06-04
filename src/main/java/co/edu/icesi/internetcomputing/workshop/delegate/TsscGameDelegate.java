package co.edu.icesi.internetcomputing.workshop.delegate;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

public interface TsscGameDelegate {
	
	public Iterable<TsscGame> getAllGames();

	public TsscGame addGame(TsscGame game);

	public TsscGame getGame(Long id);
}

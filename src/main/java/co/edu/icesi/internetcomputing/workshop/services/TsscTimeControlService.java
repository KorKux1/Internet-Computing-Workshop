package co.edu.icesi.internetcomputing.workshop.services;

import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

public interface TsscTimeControlService {
	
	public boolean save(TsscTimecontrol tsscTimeControl);
		
	public TsscTimecontrol findById(long id);
	
	public Iterable<TsscTimecontrol> findAll();

	public void remove(TsscTimecontrol entity);

	public void update(TsscTimecontrol timeC);
	
}

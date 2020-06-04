package co.edu.icesi.internetcomputing.workshop.delegate;

import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

public interface TsscTimeControlDelegate {
	
	public Iterable<TsscTimecontrol> getAllTsscTimeControles();

	public TsscTimecontrol addTsscTimeControl(TsscTimecontrol timeControl);

	public TsscTimecontrol getTsscTimeControl(Long id);
}

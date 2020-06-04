package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

public interface ITsscTimeControlDao {
	public void save(TsscTimecontrol entity);
	
	public void update(TsscTimecontrol entity);
		
	public void delete(TsscTimecontrol entity);
		
	public TsscTimecontrol findById(Long id);
		
	public List<TsscTimecontrol> findAll();
}

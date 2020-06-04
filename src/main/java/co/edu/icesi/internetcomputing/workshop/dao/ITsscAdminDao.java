package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

public interface ITsscAdminDao {
	
	public void save(TsscAdmin entity);
	
	public void update(TsscAdmin entity);
	
	public void delete(TsscAdmin entity);
	
	public TsscAdmin findById(Long id);
	
	public List<TsscAdmin> findByUsername(String name);
	
	public List<TsscAdmin> findAll();
	
}

package co.edu.icesi.internetcomputing.workshop.services;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

public interface TsscAdminService {
	
	public TsscAdmin save(TsscAdmin tsscAdmin);
	
	public void remove(TsscAdmin tsscAdmin);
	
	public Iterable<TsscAdmin> getAllAdmins();
	
	//public TsscAdmin findByUsername(String name);
	
	public TsscAdmin findById(Long id);

}

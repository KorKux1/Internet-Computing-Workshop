package co.edu.icesi.internetcomputing.workshop.delegate;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

public interface TsscAdminDelegate {
	public Iterable<TsscAdmin> getAllAdmins();
	
	public TsscAdmin getAdmin(Long id);

	public TsscAdmin addTsscAdmin(TsscAdmin admin);

}

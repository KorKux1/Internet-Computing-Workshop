package co.edu.icesi.internetcomputing.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService {
	
	@Autowired
	private TsscAdminRepository tsscAdminRepository;
	
	@Override
	public TsscAdmin save(TsscAdmin tsscAdmin) {
		return tsscAdminRepository.save(tsscAdmin);
	}

	@Override
	public void remove(TsscAdmin tsscAdmin) {
		tsscAdminRepository.delete(tsscAdmin);	
	}

}

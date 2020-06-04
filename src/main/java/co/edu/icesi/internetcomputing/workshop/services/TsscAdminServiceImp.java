package co.edu.icesi.internetcomputing.workshop.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.dao.TsscAdminDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscAdminRepository;

@Service
public class TsscAdminServiceImp implements TsscAdminService {
	
	@Autowired
	private TsscAdminDao tsscAdminDao;
	
	
	@Override
	@Transactional
	public void save(TsscAdmin tsscAdmin) {
		tsscAdminDao.save(tsscAdmin);
	}

	@Override
	@Transactional
	public void remove(TsscAdmin tsscAdmin) {
		tsscAdminDao.delete(tsscAdmin);	
	}

	@Override
	@Transactional
	public Iterable<TsscAdmin> getAllAdmins() {
		return tsscAdminDao.findAll();
	}

	@Override
	@Transactional
	public TsscAdmin findById(Long id) {
		return tsscAdminDao.findById(id);
	}

	@Override
	public void update(TsscAdmin tsscAdmin) {
		tsscAdminDao.update(tsscAdmin);;
	}

}

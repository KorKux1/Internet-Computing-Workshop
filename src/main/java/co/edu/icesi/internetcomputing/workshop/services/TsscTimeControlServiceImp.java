package co.edu.icesi.internetcomputing.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.dao.TsscTimeControlDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;
@Service
public class TsscTimeControlServiceImp implements TsscTimeControlService{
	
	private TsscTimeControlDao tsscTimeControlDao;
	
	@Autowired
	public TsscTimeControlServiceImp(TsscTimeControlDao tsscTimeControlDao) {
		this.tsscTimeControlDao = tsscTimeControlDao;
	}

	@Transactional
	public boolean save(TsscTimecontrol tsscTimeControl) {
		
		if (tsscTimeControlDao == null) {
			throw new NullPointerException("El topic no puede ser Nulo");
		}
		
		
		tsscTimeControlDao.save(tsscTimeControl);		
		return true;
	}
	
	public void update(TsscTimecontrol timecontrol) {
		tsscTimeControlDao.update(timecontrol);
	}
	
	@Override
	public void remove(TsscTimecontrol entity) {
		tsscTimeControlDao.delete(entity);
	}

	@Override
	public TsscTimecontrol findById(long id) {
		return tsscTimeControlDao.findById(id);
	}
	
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return tsscTimeControlDao.findAll();
	}

}

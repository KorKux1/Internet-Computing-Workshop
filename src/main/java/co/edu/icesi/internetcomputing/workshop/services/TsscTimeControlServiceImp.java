package co.edu.icesi.internetcomputing.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.internetcomputing.workshop.dao.TsscTimeControlDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

@Service
public class TsscTimeControlServiceImp implements TsscTimeControlService {
	
	@Autowired
	private TsscTimeControlDao tsscTimeControlDao;
	
	@Override
	public void save(TsscTimecontrol tsscTimeControl) {
		tsscTimeControlDao.save(tsscTimeControl);
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

	@Override
	public void update(TsscTimecontrol timeC) {
		tsscTimeControlDao.update(timeC);
	}

}

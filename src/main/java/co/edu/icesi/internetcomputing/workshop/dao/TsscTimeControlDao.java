package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimeControlDao implements ITsscTimeControlDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(TsscTimecontrol entity) {
		entityManager.persist(entity);	
	}

	@Override
	@Transactional
	public void update(TsscTimecontrol entity) {
		entityManager.merge(entity);
		
	}

	@Override
	@Transactional
	public void delete(TsscTimecontrol entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));	
	}

	@Override
	@Transactional
	public TsscTimecontrol findById(Long id) {
		return entityManager.find(TsscTimecontrol.class, id);
	}

	@Override
	@Transactional
	public List<TsscTimecontrol> findAll() {
		String jpql = "SELECT time FROM TsscTimecontrol time";
		return entityManager.createQuery(jpql).getResultList();
	}

}

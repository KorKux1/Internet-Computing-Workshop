package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDao implements ITsscStoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(TsscStory entity) {
		entityManager.persist(entity);
	}

	@Override
	@Transactional
	public void update(TsscStory entity) {
		entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void delete(TsscStory entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		
	}

	@Override
	@Transactional
	public TsscStory findById(Long id) {
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	@Transactional
	public List<TsscStory> findAll() {
		String jpql = "SELECT story FROM TsscStory story";
		return entityManager.createQuery(jpql).getResultList();
	}

}

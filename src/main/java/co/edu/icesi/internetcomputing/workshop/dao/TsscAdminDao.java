/**
 * 
 */
package co.edu.icesi.internetcomputing.workshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

/**
 * @author ASUS
 *
 */
@Repository
@Scope("singleton")
public class TsscAdminDao implements ITsscAdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(TsscAdmin entity) {
		entityManager.persist(entity);
	}
	
	@Override
	@Transactional
	public void update(TsscAdmin entity) {
		entityManager.merge(entity);
	}
	
	@Override
	@Transactional
	public void delete(TsscAdmin entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	

	@Override
	@Transactional
	public List<TsscAdmin> findByUsername(String name) {
		String jpql = "SELECT admin FROM TsscAdmin admin WHERE admin.username ='"+name+"'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscAdmin> findAll() {
		String jpql = "SELECT admin FROM TsscAdmin admin";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public TsscAdmin findById(Long id) {
		return entityManager.find(TsscAdmin.class, id);
	}

}

package co.edu.icesi.internetcomputing.workshop.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.dtos.TopicWithGames;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDao implements ITsscTopicDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public void save(TsscTopic entity) {
		entityManager.persist(entity);
		
	}

	@Transactional
	@Override
	public void update(TsscTopic entity) {
		entityManager.merge(entity);
		
	}
	
	@Transactional
	@Override
	public void delete(TsscTopic entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		
	}

	@Transactional
	@Override
	public TsscTopic findById(long id) {
		return entityManager.find(TsscTopic.class, id);
	}

	@Transactional
	@Override
	public List<TsscTopic> findByName(String name) {
		String jpql = "SELECT topic FROM TsscTopic topic WHERE topic.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional
	@Override
	public List<TsscTopic> findByDescription(String description) {
		String jpql = "SELECT topic from TsscTopic topic WHERE topic.description = '" + description + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional
	@Override
	public List<TsscTopic> findAll() {
		String jpql = "SELECT topic from TsscTopic topic";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Transactional
	@Override
	public List<TopicWithGames> findTopicWithGamesByDate(LocalDate date){
		String jpql = "SELECT new co.edu.icesi.internetcomputing.workshop.dtos(topic, COUNT(games)) "
				+ "FROM TsscTopic topic JOIN TsscGames games ON topic.id = games.id "
				+ "WHERE games.scheduledDate ='"+ date+"'"
				+ "GROUP BY topic "
				+ "ORDER BY topic.scheduledDate";
		
		return entityManager.createQuery(jpql).getResultList();
	}
	

}

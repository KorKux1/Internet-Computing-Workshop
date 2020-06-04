package co.edu.icesi.internetcomputing.workshop.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;

@Repository
@Scope("singleton")
public class TsscGameDao implements ITsscGameDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(TsscGame entity) {
		entityManager.persist(entity);
	}

	@Override
	@Transactional
	public void update(TsscGame entity) {
		entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void delete(TsscGame entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	@Transactional
	public TsscGame findById(long id) {
		return entityManager.find(TsscGame.class, id);
	}

	@Override
	@Transactional
	public List<TsscGame> findAll() {
		String jpql = "SELECT game FROM TsscGame game";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByName(String name) {
		String jpql = "SELECT game FROM TsscGame game WHERE game.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByDescription(String description) {
		String jpql = "SELECT game FROM TsscGame game WHERE game.name = '" + description + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByTopicId(long topicId) {
		String jpql = "SELECT game FROM TsscGame game, TsscTopic topic WHERE topic.id = '" + topicId + "' AND game.tsscTopic.id = topic.id";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByRangeDates(LocalDate startDate, LocalDate endDate) {
		String jpql = "SELECT game FROM TsscGame game WHERE game.scheduledDate BETWEEN '" + startDate + "' AND '" + endDate + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByDateHours(LocalDate startDate, LocalDate endDate, LocalTime startTime,
			LocalTime endTime) {
		String jpql = "SELECT game FROM TsscGame game WHERE game.scheduledDate BETWEEN '" + startDate + "' AND '" + endDate + "' AND game.scheduledTime BETWEEN '" + startTime +"' AND '" + endTime + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public List<TsscGame> findByCountStoryOrNotTimeGame(LocalDate date) {
		String jpql = "SELECT game FROM TsscGame game WHERE game.scheduledDate = '" + date + "' and (11 > (SELECT count(story) FROM TsscGame.tsscStory story) or game.tsscTimecontrols is null)";
		return entityManager.createQuery(jpql).getResultList();
	}

}

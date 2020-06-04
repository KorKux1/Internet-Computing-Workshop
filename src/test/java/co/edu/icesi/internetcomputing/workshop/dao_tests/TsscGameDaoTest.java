package co.edu.icesi.internetcomputing.workshop.dao_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.dao.ITsscGameDao;
import co.edu.icesi.internetcomputing.workshop.dao.TsscStoryDao;
import co.edu.icesi.internetcomputing.workshop.dao.TsscTopicDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
@Rollback(false)
public class TsscGameDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ITsscGameDao tsscGameDao;
	
	@Autowired
	private TsscTopicDao tsscTopicDao;
	
	@Autowired
	private TsscStoryDao tsscStoryDao;
	
	private TsscGame tsscGame;
	
	private TsscTopic tsscTopic;
	
	@BeforeClass
	public void initialize() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setPriority(new BigDecimal(5));
		tsscStory.setDescription("Story");
		tsscStory.setInitialSprint(new BigDecimal(10));
		tsscStory.setBusinessValue(new BigDecimal(10));
		
		List<TsscStory> tsscStories = new ArrayList<TsscStory>();
		tsscStories.add(tsscStory);
		
		tsscGame = new TsscGame();
		
		tsscGame.setName("Game");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setScheduledDate(LocalDate.of(2020, 05, 12));
		tsscGame.setScheduledTime(LocalTime.of(10, 20));
		tsscGame.setTsscStories(tsscStories);
		
		tsscTopic = new TsscTopic();
		tsscTopic.setDescription("Topic Description");
		tsscTopic.setDefaultSprints(5);
		tsscTopic.setDefaultGroups(5);
		tsscTopic.setGroupPrefix("T");
		
		tsscGame.setTsscTopic(tsscTopic);
		
		tsscStoryDao.save(tsscStory);
		tsscTopicDao.save(tsscTopic);
		tsscGameDao.save(tsscGame);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveGame() {
		assertNotNull(tsscGameDao);
		
		TsscGame tsscGameAux = new TsscGame();
		
		tsscGameAux.setName("GameAux");
		tsscGameAux.setNGroups(5);
		tsscGameAux.setNSprints(5);
		try {
			tsscGameDao.save(tsscGameAux);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		TsscGame game = tsscGameDao.findById(tsscGame.getId());
		
		assertEquals(game.getName(), tsscGame.getName());
		assertTrue(game.getNGroups() == tsscGame.getNGroups() );
		assertTrue(game.getNSprints() == tsscGame.getNSprints());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateGame() {
		TsscGame game = tsscGameDao.findById(tsscGame.getId());
		
		assertNotNull(game);
		
		game.setName("Game Updated");
		game.setNGroups(10);
		game.setNSprints(10);
		
		tsscGameDao.update(game);
		
		TsscGame gameUpdated = tsscGameDao.findById(tsscGame.getId());
		
		assertEquals(gameUpdated.getName(), game.getName());
		assertTrue(gameUpdated.getNSprints() == game.getNSprints());
		assertTrue(gameUpdated.getNGroups() == game.getNGroups());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscGame> games = tsscGameDao.findAll();
		assertTrue(games.size() == 1);
		
		TsscGame tsscGameUpdated = new TsscGame();
		tsscGameUpdated.setName("Game 2");;
		tsscGameUpdated.setNGroups(5);
		tsscGameUpdated.setNSprints(5);;
		
		tsscGameDao.save(tsscGameUpdated);
		
		games = tsscGameDao.findAll();
		assertTrue(games.size() == 2);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {
		TsscGame tsscGameFound = tsscGameDao.findByName(tsscGame.getName()).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDescription() {
		TsscGame tsscGameFound = tsscGameDao.findByDescription(tsscGame.getName()).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByTopicId() {
		TsscGame tsscGameFound = tsscGameDao.findByTopicId(tsscTopic.getId()).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
				   rollbackFor = Exception.class)
	public void testFindByRangeDatesd() {
		TsscGame tsscGameFound = tsscGameDao.findByRangeDates(LocalDate.of(2020, 05, 11), 
															  LocalDate.of(2020, 05, 13)).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
				   rollbackFor = Exception.class)
	public void testFindByDateHours() {
		TsscGame tsscGameFound = tsscGameDao.findByDateHours(LocalDate.of(2020, 05, 12), 
															 LocalDate.of(2020, 05, 13),
															 LocalTime.of(9, 0),
															 LocalTime.of(11, 30)).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}
	
	
	/*@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, 
				   rollbackFor = Exception.class)
	public void testFindByCountStoryMinor10() {
		TsscGame tsscGameFound = tsscGameDao.findByCountStoryOrNotTimeGame(LocalDate.of(2020, 05, 12)).get(0);
		
		assertEquals(tsscGameFound.getName(), tsscGame.getName());
		assertTrue(tsscGameFound.getId() == tsscGame.getId());
		assertTrue(tsscGameFound.getNGroups() == tsscGame.getNGroups());
		assertTrue(tsscGameFound.getNSprints() == tsscGame.getNSprints());
	}*/
	
	@Test(priority = 50)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {		
		List<TsscGame> games = tsscGameDao.findAll();
		
		for (TsscGame tsscGame : games) {
			tsscGameDao.delete(tsscGame);
		}
		
		games = tsscGameDao.findAll();
		
		assertTrue(games.isEmpty());
	}
}

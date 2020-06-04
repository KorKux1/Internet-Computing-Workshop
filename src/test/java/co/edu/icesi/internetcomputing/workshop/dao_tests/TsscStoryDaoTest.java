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
import co.edu.icesi.internetcomputing.workshop.dao.TsscStoryDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
@Rollback(false)
public class TsscStoryDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private TsscStoryDao tsscStoryDao;
	
	private TsscStory tsscStory;
	
	@BeforeMethod
	public void initialize() {
		tsscStory = new TsscStory();
		tsscStory.setPriority(new BigDecimal(5));
		tsscStory.setDescription("Story");
		tsscStory.setInitialSprint(new BigDecimal(10));
		tsscStory.setBusinessValue(new BigDecimal(10));
		
		tsscStoryDao.save(tsscStory);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveStory() {
		assertNotNull(tsscStoryDao);
		
		TsscStory tssStoryAux = new TsscStory();
		
		tssStoryAux.setPriority(new BigDecimal(5));
		tssStoryAux.setDescription("Story");
		tssStoryAux.setInitialSprint(new BigDecimal(10));
		tssStoryAux.setBusinessValue(new BigDecimal(10));
		
		try {
			tsscStoryDao.save(tssStoryAux);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		TsscStory story = tsscStoryDao.findById(tsscStory.getId());
		
		assertEquals(story.getDescription(), tsscStory.getDescription());
		assertTrue(story.getPriority().doubleValue() == tsscStory.getPriority().doubleValue());
		assertTrue(story.getBusinessValue().doubleValue() == tsscStory.getBusinessValue().doubleValue());
		assertTrue(story.getInitialSprint().doubleValue() == tsscStory.getInitialSprint().doubleValue());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateStory() {
		TsscStory story = tsscStoryDao.findById(tsscStory.getId());
		
		assertNotNull(story);
		
		story.setPriority(new BigDecimal(5));
		story.setDescription("Story");
		story.setInitialSprint(new BigDecimal(10));
		story.setBusinessValue(new BigDecimal(10));
		
		tsscStoryDao.update(story);
		
		TsscStory storyUpdated = tsscStoryDao.findById(tsscStory.getId());
		
		assertEquals(story.getDescription(), storyUpdated.getDescription());
		assertTrue(story.getPriority().doubleValue() == storyUpdated.getPriority().doubleValue());
		assertTrue(story.getBusinessValue().doubleValue() == storyUpdated.getBusinessValue().doubleValue());
		assertTrue(story.getInitialSprint().doubleValue() == storyUpdated.getInitialSprint().doubleValue());
	}
	
	
	@Test(priority = 3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscStory> stories = tsscStoryDao.findAll();
		assertTrue(stories.size() == 1);
		
		TsscStory tsscStoryUpdated = new TsscStory();
		tsscStoryUpdated.setPriority(new BigDecimal(5));
		tsscStoryUpdated.setDescription("Story");
		tsscStoryUpdated.setInitialSprint(new BigDecimal(10));
		tsscStoryUpdated.setBusinessValue(new BigDecimal(10));
		
		tsscStoryDao.save(tsscStoryUpdated);
		
		stories = tsscStoryDao.findAll();
		assertTrue(stories.size() == 2);
	}
	
	@Test(priority = 2)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {

		List<TsscStory> stories = tsscStoryDao.findAll();
		for (TsscStory tsscStory : stories) {
			tsscStoryDao.delete(tsscStory);
		}
		
		stories = tsscStoryDao.findAll();
		
		assertTrue(stories.isEmpty());
	}

}

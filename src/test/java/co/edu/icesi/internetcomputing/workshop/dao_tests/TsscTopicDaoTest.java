package co.edu.icesi.internetcomputing.workshop.dao_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

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
import co.edu.icesi.internetcomputing.workshop.dao.ITsscTopicDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
@Rollback(false)
public class TsscTopicDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ITsscTopicDao tsscTopicDao;
	
	private TsscTopic tsscTopic;
	
	
	@BeforeMethod
	public void initialize() {
		tsscTopic = new TsscTopic();
		
		tsscTopic.setDefaultGroups(5);
		tsscTopic.setDefaultSprints(5);
		tsscTopic.setName("Topic");
		tsscTopic.setDescription("Topic");
		tsscTopic.setGroupPrefix("T");
		
		tsscTopicDao.save(tsscTopic);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testsaveTopic() {
		assertNotNull(tsscTopicDao);
		
		TsscTopic tssTopicAux = new TsscTopic();
		
		tssTopicAux.setDefaultGroups(5);
		tssTopicAux.setDefaultSprints(5);
		tssTopicAux.setDescription("Topic");
		tssTopicAux.setGroupPrefix("T");
		
		try {
			tsscTopicDao.save(tssTopicAux);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		TsscTopic topic = tsscTopicDao.findById(tsscTopic.getId());
		
		assertEquals(topic.getDescription(), tsscTopic.getDescription());
		assertEquals(topic.getGroupPrefix(), tsscTopic.getGroupPrefix());
		assertTrue(topic.getDefaultGroups() == tsscTopic.getDefaultGroups());
		assertTrue(topic.getDefaultSprints() == tsscTopic.getDefaultSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDescription() {
		TsscTopic topic = tsscTopicDao.findByDescription(tsscTopic.getDescription()).get(0);
		
		assertEquals(topic.getDescription(), tsscTopic.getDescription());
		assertEquals(topic.getGroupPrefix(), tsscTopic.getGroupPrefix());
		assertTrue(topic.getDefaultGroups() == tsscTopic.getDefaultGroups());
		assertTrue(topic.getDefaultSprints() == tsscTopic.getDefaultSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {
		TsscTopic topic = tsscTopicDao.findByName(tsscTopic.getName()).get(0);
		
		assertEquals(topic.getDescription(), tsscTopic.getDescription());
		assertEquals(topic.getGroupPrefix(), tsscTopic.getGroupPrefix());
		assertTrue(topic.getDefaultGroups() == tsscTopic.getDefaultGroups());
		assertTrue(topic.getDefaultSprints() == tsscTopic.getDefaultSprints());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		TsscTopic topic = tsscTopicDao.findById(tsscTopic.getId());
		
		assertNotNull(topic);
		
		topic.setDefaultGroups(5);
		topic.setDefaultSprints(5);
		topic.setDescription("Topic Updated");
		topic.setGroupPrefix("TU");
		
		
		tsscTopicDao.update(topic);
		
		TsscTopic topicUpdated = tsscTopicDao.findById(tsscTopic.getId());
		
		assertEquals(topic.getDescription(), topicUpdated.getDescription());
		assertEquals(topic.getGroupPrefix(), topicUpdated.getGroupPrefix());
		assertTrue(topic.getDefaultGroups() == topicUpdated.getDefaultGroups());
		assertTrue(topic.getDefaultSprints() == topicUpdated.getDefaultSprints());
		
	}
	
	@Test(priority = 3)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscTopic>  topics = tsscTopicDao.findAll();
		
		assertTrue(topics.size() == 1);
		TsscTopic tsscStoryAux= new TsscTopic();
		tsscStoryAux.setDefaultGroups(5);
		tsscStoryAux.setDefaultSprints(5);
		tsscStoryAux.setDescription("Topic2");
		tsscStoryAux.setGroupPrefix("T");

		tsscTopicDao.save(tsscStoryAux);
		
		topics = tsscTopicDao.findAll();
		
		assertTrue(topics.size() == 2);
	}
	
	@Test(priority = 2)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		List<TsscTopic> topics = tsscTopicDao.findAll();

		for (TsscTopic tsscTopic : topics) {
			tsscTopicDao.delete(tsscTopic);
		}
		
		topics = tsscTopicDao.findAll();
		assertTrue(topics.isEmpty());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testTopicWithGames() {
		//TODO
	}
	
}

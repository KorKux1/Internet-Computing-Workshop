package co.edu.icesi.internetcomputing.workshop.integration_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
public class TsscTopicServiceTest extends AbstractTestNGSpringContextTests  {
	
	@Autowired
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	/*@Autowired
	public TsscTopicServiceTest(TsscTopicServiceImp tsscTopicServiceImp) {
		this.tsscTopicServiceImp = tsscTopicServiceImp;
	}*/
	
	/* 
	 * SAVE TESTS
	 * */
	@Test(testName = "Add a correctly filled topic", description = "Add a topic without errors")
	public void testAddTopic1() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(1);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(3);
		
		assertTrue(tsscTopicServiceImp.save(tsscTopic));
		assertNotNull(tsscTopicServiceImp.findById(1));
		assertEquals(tsscTopicServiceImp.findById(1).getName(), tsscTopic.getName());
	}

	@Test(expectedExceptions=NoSuchElementException.class, expectedExceptionsMessageRegExp="No value present",testName = "Add a topic, groups not greater than zero", description = "Added a topic where groups are not greater than zero")
	public void testAddTopic2() {

		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(2);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 2");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(0);
		tsscTopic.setDefaultSprints(3);
		assertFalse(tsscTopicServiceImp.save(tsscTopic));
		assertNotNull(tsscTopicServiceImp.findById(1));
		
	}
	
	@Test(expectedExceptions=NoSuchElementException.class,expectedExceptionsMessageRegExp="No value present", testName = "Add a topic, Sprints not greater than zero", description = "Added a topic where sprints are not greater than zero")
	public void testAddTopic3() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(1);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 3");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(0);
		
		assertFalse(tsscTopicServiceImp.save(tsscTopic));
		assertNotNull(tsscTopicServiceImp.findById(1));

	}
	
	@Test(testName = "Add a Null topic", description = "Attempt to add null topic",expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp="El topic no puede ser Nulo")
	public void testAddTopicNull() {
		
		TsscTopic tsscTopic = null;
		tsscTopicServiceImp.save(tsscTopic);
	}
	
	/*
	 * UPDATE TESTS
	 * */
	@Test(groups = {"Update"},testName = "Update the Topic with ID 0", description = "Update the topic without errors")
	public void testUpdateTopic1() {
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(1);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(4);
		tsscTopic.setDefaultSprints(4);
		tsscTopicServiceImp.save(tsscTopic);
		
		TsscTopic aux = tsscTopicServiceImp.findById(tsscTopic.getId());
		aux.setName("Topic 1 Updated");
		aux.setDescription("Description Updated");
		aux.setGroupPrefix("G1U");
		
				
		assertTrue(tsscTopicServiceImp.save(aux));
		assertEquals(tsscTopicServiceImp.findById(aux.getId()).getName(), aux.getName());
	}
	
	@Test(groups = {"Update"}, testName = "Update Topic With ID 0, groups not greater than zero", description = "Update a topic where groups are not greater than zero")
	public void testUpdateTopic2() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(1);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(4);
		tsscTopic.setDefaultSprints(4);
		tsscTopicServiceImp.save(tsscTopic);

		
		TsscTopic aux = tsscTopicServiceImp.findById(tsscTopic.getId());
		aux.setName("Topic 1 Updated");
		aux.setDefaultGroups(0);
		aux.setDefaultSprints(4);
		aux.setDescription("Description Updated");
		aux.setGroupPrefix("G1U");
		
		assertFalse(tsscTopicServiceImp.save(aux));
		assertNotEquals(tsscTopicServiceImp.findById(aux.getId()).getName(), aux.getName());
	}
	
	@Test(groups = {"Update"}, testName = "Update Topic With ID 0, Sprints not greater than zero", description = "Update a topic where sprints are not greater than zero")
	public void testUpdateTopic3() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 3");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(4);
		tsscTopic.setDefaultSprints(4);
		tsscTopicServiceImp.save(tsscTopic);


		TsscTopic aux = tsscTopicServiceImp.findById(tsscTopic.getId());
		aux.setName("Topic 1 Updated");
		aux.setDefaultGroups(4);
		aux.setDefaultSprints(0);
		aux.setDescription("Description Updated");
		aux.setGroupPrefix("G1U");
		
		assertFalse(tsscTopicServiceImp.save(aux));
		assertNotEquals(tsscTopicServiceImp.findById(aux.getId()).getName(), aux.getName());
	}
}

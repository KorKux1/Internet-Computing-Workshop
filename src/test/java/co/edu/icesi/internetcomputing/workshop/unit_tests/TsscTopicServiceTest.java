package co.edu.icesi.internetcomputing.workshop.unit_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.testng.Assert.assertFalse;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

public class TsscTopicServiceTest  {
	
	@Mock
	private TsscTopicRepository tsscTopicRepository;
	
	@InjectMocks
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	@BeforeMethod(alwaysRun = true)
	public void iniMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	/* 
	 * SAVE TESTS
	 * */
	@Test(testName = "Add a correctly filled topic", description = "Add a topic without errors")
	public void testAddTopic1() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(3);
		
		assertTrue(tsscTopicServiceImp.save(tsscTopic));
		//verify(tsscTopicRepository).save(tsscTopic);
		//verifyNoMoreInteractions(tsscTopicRepository);
	}
	
	
	@Test(testName = "Add a topic, groups not greater than zero", description = "Added a topic where groups are not greater than zero")
	public void testAddTopic2() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 2");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(0);
		tsscTopic.setDefaultSprints(3);
		
		assertFalse(tsscTopicServiceImp.save(tsscTopic));
		verifyZeroInteractions(tsscTopicRepository);
	}
	
	@Test(testName = "Add a topic, Sprints not greater than zero", description = "Added a topic where sprints are not greater than zero")
	public void testAddTopic3() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 3");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(0);
		
		assertFalse(tsscTopicServiceImp.save(tsscTopic));	
		verifyZeroInteractions(tsscTopicRepository);
	}
	
	@Test(testName = "Add a Null topic", description = "Attempt to add null topic",expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp="El topic no puede ser Nulo")
	public void testAddTopicNull() {
		
		TsscTopic tsscTopic = null;
		tsscTopicServiceImp.save(tsscTopic);
		verifyZeroInteractions(tsscTopic);

	}
	
	/*
	 * UPDATE TESTS
	 * */
	@BeforeGroups("Update")
	public void prepareUpdateTests() {
		tsscTopicServiceImp.removeAll();
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(3);
		
		tsscTopicServiceImp.save(tsscTopic);
	}
	
	@Test(groups = {"Update"},testName = "Update the Topic with ID 0", description = "Update the topic without errors")
	public void testUpdateTopic1() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description Updated");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1 Updated");
		tsscTopic.setGroupPrefix("G1U");
		tsscTopic.setDefaultGroups(4);
		tsscTopic.setDefaultSprints(4);
		
		
		assertTrue(tsscTopicServiceImp.save(tsscTopic));
		verify(tsscTopicRepository).save(tsscTopic);
		verifyNoMoreInteractions(tsscTopicRepository);
	}
		
	@Test(groups = {"Update"}, testName = "Update Topic With ID 0, groups not greater than zero", description = "Update a topic where groups are not greater than zero")
	public void testUpdateTopic2() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description Updated");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1 Updated");
		tsscTopic.setGroupPrefix("G1U");
		tsscTopic.setDefaultGroups(0);
		tsscTopic.setDefaultSprints(4);
		
		assertFalse(tsscTopicServiceImp.save(tsscTopic));
		verifyZeroInteractions(tsscTopicRepository);
	}
	
	@Test(groups = {"Update"}, testName = "Update Topic With ID 0, Sprints not greater than zero", description = "Update a topic where sprints are not greater than zero")
	public void testUpdateTopic3() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description Updated");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 3 Updated");
		tsscTopic.setGroupPrefix("G1U");
		tsscTopic.setDefaultGroups(4);
		tsscTopic.setDefaultSprints(0);
		
		assertFalse(tsscTopicServiceImp.save(tsscTopic));	
		verifyZeroInteractions(tsscTopicRepository);
	}
}

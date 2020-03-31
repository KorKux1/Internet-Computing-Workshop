package co.edu.icesi.internetcomputing.workshop.integration_tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
public class TsscGameServiceTest extends AbstractTestNGSpringContextTests {
	
	
	@Autowired
	private TsscGameServiceImp tsscGameServiceImp;
	
	@Autowired
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	@Autowired
	private TsscStoryServiceImp tsscStoryServiceImp;
	
	private TsscTopic tsscTopic;
	
	private TsscStory tssStory;
	
	Optional<TsscTopic> tsscTopics;
	
	Optional<TsscGame> tsscGamesU;
	
	private TsscGame tsscGameU;
	
	@BeforeClass
	public void initialize() {
		List<TsscStory> tsscStories = new ArrayList<>();
		tssStory = new TsscStory();
		tssStory.setId(1);
		tssStory.setDescription("Story 1");
		tssStory.setInitialSprint(new BigDecimal(5));
		tssStory.setPriority(new BigDecimal(3));
		tssStory.setBusinessValue(new BigDecimal(4));
		
		tsscStoryServiceImp.save(tssStory);
		
		tsscTopic = new TsscTopic();
		tsscTopic.setId(1);
		tsscTopic.setName("Topic 1");
		tsscTopic.setDescription("Topic for Game");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopicServiceImp.save(tsscTopic);
		tsscTopic.setTsscStories(tsscStories);
		
	}
	
	/*
	 * Save Tests
	 * */
	@Test
	public void testAddGame1() {	
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(1);
		tsscGame.setName("Game 1");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopic);
		
		assertTrue(tsscGameServiceImp.save(tsscGame));
		assertNotNull(tsscGameServiceImp.findById(tsscGame.getId()).get());
		assertEquals(tsscGameServiceImp.findById(tsscGame.getId()).get().getName(), tsscGame.getName());
	}
	
	@Test(expectedExceptions=NoSuchElementException.class, expectedExceptionsMessageRegExp="No value present")
	public void testAddGame2() {		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 2");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopic);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
		assertNull(tsscGameServiceImp.findById(tsscGame.getId()).get());
	}
	
	@Test(expectedExceptions=NoSuchElementException.class, expectedExceptionsMessageRegExp="No value present")
	public void testAddGame3() {
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 3");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(0);
		tsscGame.setTsscTopic(tsscTopic);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
		assertNull(tsscGameServiceImp.findById(tsscGame.getId()).get());
	}
	
	@Test(expectedExceptions = JpaObjectRetrievalFailureException.class)
	public void testAddGame4() {
		TsscTopic tsscTopicNoExist = new TsscTopic();
		tsscTopicNoExist.setId(4);
		tsscTopicNoExist.setName("Topic 1");
		tsscTopicNoExist.setDescription("Topic 1");
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(1);
		tsscGame.setName("Game 3");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopicNoExist);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
	}
	
	@Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp="El Game no puede ser nulo")
	public void testAddTopicNull() {
		
		TsscGame tsscGame = null;
		tsscGameServiceImp.save(tsscGame);
	}
	
	
	public void testAdd2Game1() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 1");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		
		tsscGame.setTsscTopic(tsscTopic);
		
		tsscGameServiceImp.save2(tsscGame);
		
		assertEquals(tsscTopic.getTsscStories().get(0).getDescription(), tsscGame.getTsscStories().get(0).getDescription());
	}
	
	/*
	 * UPDATE TESTS
	 * */
	/*@BeforeMethod
	public void initializeUpdate(){
		tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setName("Topic 1");
		tsscTopic.setDescription("Topic for Game");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopics = Optional.of(tsscTopic);
		
		tsscGameU = new TsscGame();
		tsscGameU.setId(1);
		tsscGameU.setName("Game 1");
		tsscGameU.setNGroups(5);
		tsscGameU.setNSprints(5);
		tsscGameU.setTsscTopic(tsscTopic);
		tsscGamesU = Optional.of(tsscGameU);
	}
	
	@Test(groups="Update")
	public void testUpdateGame1() {		
		when(tsscGameRepository.findById(tsscGameU.getId())).thenReturn(tsscGamesU);
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);

		
		Optional<TsscGame> tsscGames = tsscGameRepository.findById(tsscGameU.getId());
		TsscGame tsscGame = tsscGames.get();
		tsscGame.setName("Game Updated");
		
		assertTrue(tsscGameServiceImp.save(tsscGame));
	
		
		verify(tsscGameRepository).save(tsscGame);
		verify(tsscTopicRepository).findById(tsscTopic.getId());

		verifyNoMoreInteractions(tsscTopicRepository);
	}
	
	
	@Test(groups="Update")
	public void testUpdateGame2() {		
		when(tsscGameRepository.findById(tsscGameU.getId())).thenReturn(tsscGamesU);
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);

		
		Optional<TsscGame> tsscGames = tsscGameRepository.findById(tsscGameU.getId());
		TsscGame tsscGame = tsscGames.get();
		tsscGame.setNGroups(0);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));	
	}
	
	@Test(groups="Update")
	public void testUpdateGame3() {		
		when(tsscGameRepository.findById(tsscGameU.getId())).thenReturn(tsscGamesU);
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);

		Optional<TsscGame> tsscGames = tsscGameRepository.findById(tsscGameU.getId());
		TsscGame tsscGame = tsscGames.get();
		tsscGame.setNSprints(0);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
	}*/
	

}

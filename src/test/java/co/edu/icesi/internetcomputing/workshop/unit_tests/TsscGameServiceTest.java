package co.edu.icesi.internetcomputing.workshop.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;

public class TsscGameServiceTest {
	
	@Mock
	private TsscGameRepository tsscGameRepository;
	
	@Mock
	private TsscTopicRepository tsscTopicRepository;
	
	@Mock
	private TsscStoryRepository tsscStoryRespository;
	
	
	@InjectMocks
	private TsscGameServiceImp tsscGameServiceImp;
	
	
	private TsscTopic tsscTopic;
	
	Optional<TsscTopic> tsscTopics;
	
	Optional<TsscGame> tsscGamesU;
	
	private TsscGame tsscGameU;
	
	private TsscStory tsscStory;
	
	private List<TsscStory> tsscStories;
	
	@BeforeClass
	public void initialize() {
		tsscStories = new ArrayList<>();
		tsscStory = new TsscStory();
		tsscStory.setId(1);
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setInitialSprint(new BigDecimal(4));
		tsscStory.setPriority(new BigDecimal(4));
		tsscStory.setDescription("Story");
		tsscStories.add(tsscStory);
		
		tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setName("Topic 1");
		tsscTopic.setDescription("Topic for Game");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setTsscStories(tsscStories);
		
		tsscTopics = Optional.of(tsscTopic);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void iniMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * Save Tests
	 * */
	@Test
	public void testAddGame1() {
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 1");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopic);
		
		tsscGameServiceImp.save(tsscGame);
		
		verify(tsscGameRepository).save(tsscGame);
		verify(tsscTopicRepository).findById(tsscTopic.getId());
		
		verifyNoMoreInteractions(tsscGameRepository);
		verifyNoMoreInteractions(tsscTopicRepository);	
	}
	
	@Test
	public void testAddGame2() {
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 2");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(0);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopic);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
		verifyZeroInteractions(tsscGameRepository);	
	}
	
	@Test
	public void testAddGame3() {
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 3");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(0);
		tsscGame.setTsscTopic(tsscTopic);
		
		assertFalse(tsscGameServiceImp.save(tsscGame));
		verifyZeroInteractions(tsscGameRepository);	
	}
	
	@Test
	public void testAddGame4() {
		TsscTopic tsscTopicNoExist = new TsscTopic();
		tsscTopicNoExist.setId(10000);
		tsscTopicNoExist.setName("Topic 1");
		tsscTopicNoExist.setDescription("Topic 1");
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 3");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setTsscTopic(tsscTopicNoExist);
		
		when(tsscTopicRepository.findById(tsscTopicNoExist.getId())).thenReturn(null);

		assertFalse(tsscGameServiceImp.save(tsscGame));
		verifyZeroInteractions(tsscGameRepository);	
	}
	
	@Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp="El Game no puede ser nulo")
	public void testAddTopicNull() {
		
		TsscGame tsscGame = null;
		tsscGameServiceImp.save(tsscGame);
		verifyZeroInteractions(tsscTopic);

	}
	
	@Test
	public void testAdd2Game1() {
		when(tsscTopicRepository.findById(tsscTopic.getId())).thenReturn(tsscTopics);
		
		TsscGame tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setName("Game 1");
		tsscGame.setAdminPassword("123");
		tsscGame.setGuestPassword("123456");
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscTopic.setTsscStories(tsscStories);
		tsscGame.setTsscTopic(tsscTopic);
		tsscGame.setTsscStories(new ArrayList<TsscStory>());
		tsscGameServiceImp.save2(tsscGame);
		
		assertEquals(tsscTopic.getTsscStories().get(0).getDescription(), tsscGame.getTsscStories().get(0).getDescription());
		verify(tsscGameRepository).save(tsscGame);
		verify(tsscTopicRepository).findById(tsscTopic.getId());
		
		verifyNoMoreInteractions(tsscGameRepository);
		verifyNoMoreInteractions(tsscTopicRepository);	
	}
	
	/*
	 * UPDATE TESTS
	 * */
	@BeforeMethod
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
	}
	

}

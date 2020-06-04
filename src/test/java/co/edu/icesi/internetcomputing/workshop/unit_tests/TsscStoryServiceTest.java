package co.edu.icesi.internetcomputing.workshop.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;

public class TsscStoryServiceTest {
	
	@Mock
	private TsscStoryRepository tsscStoryRepository;
	
	@Mock
	private TsscGameRepository tsscGameRepository;
	
	@InjectMocks
	private TsscStoryServiceImp tsscStoryServiceImp;
	
	private TsscGame tsscGame;
	
	private Optional<TsscGame> tsscGames;
	
	private TsscGame tsscGameU;
	
	private Optional<TsscGame> tsscGamesU;
	
	private TsscStory tsscStoryUpdate;
	private Optional<TsscStory> tsscStories;
	
	@BeforeMethod(alwaysRun = true)
	public void iniMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public void initialize() {
		tsscGame = new TsscGame();
		tsscGame.setId(0);
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setName("Game 1");

		tsscGames = Optional.of(tsscGame);
	}
	
	@Test
	public void testAddStory1() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(5));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
		when(tsscGameRepository.findById(tsscStory.getTsscGame().getId())).thenReturn(tsscGames);

		
		tsscStoryServiceImp.save(tsscStory);
		
		verify(tsscStoryRepository).save(tsscStory);
		verify(tsscGameRepository).findById(tsscStory.getTsscGame().getId());
		
		verifyNoMoreInteractions(tsscStoryRepository);
		verifyNoMoreInteractions(tsscStoryRepository);	
	}
	
	@Test
	public void testAddGame2() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(5));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(0));
		tsscStory.setTsscGame(tsscGame);
		when(tsscGameRepository.findById(tsscStory.getTsscGame().getId())).thenReturn(tsscGames);

		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		verifyZeroInteractions(tsscStoryRepository);	
	}
	
	@Test
	public void testAddGame3() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(0));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
		
		when(tsscGameRepository.findById(tsscStory.getTsscGame().getId())).thenReturn(tsscGames);

		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		verifyZeroInteractions(tsscStoryRepository);	
	}
	
	@Test
	public void testAddGame4() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(3));
		tsscStory.setPriority(new BigDecimal(0));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
		
		when(tsscGameRepository.findById(tsscStory.getTsscGame().getId())).thenReturn(tsscGames);

		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		verifyZeroInteractions(tsscStoryRepository);	
	}
	
	@Test
	public void testAddGame5() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(3));
		tsscStory.setPriority(new BigDecimal(0));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
		
		when(tsscGameRepository.findById(tsscStory.getTsscGame().getId())).thenReturn(null);

		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		verifyZeroInteractions(tsscStoryRepository);	
	}
	
	/*
	 * UPDATE TESTS
	 * */
	
	@BeforeMethod
	public void initializeUpdate(){
		tsscGameU = new TsscGame();
		tsscGameU.setId(1);
		tsscGameU.setName("Game 1");
		tsscGameU.setNGroups(5);
		tsscGameU.setNSprints(5);
		tsscGamesU = Optional.of(tsscGameU);
		
		tsscStoryUpdate = new TsscStory();
		tsscStoryUpdate.setId(0);
		tsscStoryUpdate.setDescription("Story 1");
		tsscStoryUpdate.setInitialSprint(new BigDecimal(5));
		tsscStoryUpdate.setPriority(new BigDecimal(3));
		tsscStoryUpdate.setBusinessValue(new BigDecimal(4));
		tsscStoryUpdate.setTsscGame(tsscGameU);
		tsscStories = Optional.of(tsscStoryUpdate);
	}
	
	@Test(groups="Update")
	public void testUpdateStory1() {		
		when(tsscStoryRepository.findById(tsscStoryUpdate.getId())).thenReturn(tsscStories);

		Optional<TsscStory> tsscStoriesFound = tsscStoryRepository.findById(tsscStoryUpdate.getId());
		TsscStory tsscFound = tsscStoriesFound.get();
		tsscFound.setDescription("Story Updated");
		
		when(tsscGameRepository.findById(tsscStoryUpdate.getTsscGame().getId())).thenReturn(tsscGamesU);

		
		assertTrue(tsscStoryServiceImp.save(tsscFound));
			
		verify(tsscStoryRepository).save(tsscFound);
		
		verify(tsscStoryRepository).findById(tsscStoryUpdate.getId());

		verifyNoMoreInteractions(tsscStoryRepository);
	}
	
	@Test
	public void testUpdateStory2() {		
		when(tsscStoryRepository.findById(tsscStoryUpdate.getId())).thenReturn(tsscStories);

		Optional<TsscStory> tsscStoriesFound = tsscStoryRepository.findById(tsscStoryUpdate.getId());
		TsscStory tsscFound = tsscStoriesFound.get();
		tsscFound.setDescription("Story Updated");
		tsscFound.setBusinessValue(new BigDecimal(0));
		
		when(tsscGameRepository.findById(tsscStoryUpdate.getTsscGame().getId())).thenReturn(tsscGamesU);

		
		assertFalse(tsscStoryServiceImp.save(tsscFound));
	
	}
	
	@Test
	public void testUpdateStory3() {		
		when(tsscStoryRepository.findById(tsscStoryUpdate.getId())).thenReturn(tsscStories);

		Optional<TsscStory> tsscStoriesFound = tsscStoryRepository.findById(tsscStoryUpdate.getId());
		TsscStory tsscFound = tsscStoriesFound.get();
		tsscFound.setDescription("Story Updated");
		tsscFound.setInitialSprint(new BigDecimal(0));
		
		when(tsscGameRepository.findById(tsscStoryUpdate.getTsscGame().getId())).thenReturn(tsscGamesU);

		
		assertFalse(tsscStoryServiceImp.save(tsscFound));
	
	}
	
	@Test
	public void testUpdateStory4() {		
		when(tsscStoryRepository.findById(tsscStoryUpdate.getId())).thenReturn(tsscStories);

		Optional<TsscStory> tsscStoriesFound = tsscStoryRepository.findById(tsscStoryUpdate.getId());
		TsscStory tsscFound = tsscStoriesFound.get();
		tsscFound.setDescription("Story Updated");
		tsscFound.setPriority(new BigDecimal(0));
		
		when(tsscGameRepository.findById(tsscStoryUpdate.getTsscGame().getId())).thenReturn(tsscGamesU);

		assertFalse(tsscStoryServiceImp.save(tsscFound));
	
	}	
}

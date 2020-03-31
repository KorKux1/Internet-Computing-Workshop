package co.edu.icesi.internetcomputing.workshop.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;

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
	
	
	
}

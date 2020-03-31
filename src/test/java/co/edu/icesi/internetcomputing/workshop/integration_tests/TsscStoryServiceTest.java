package co.edu.icesi.internetcomputing.workshop.integration_tests;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;

public class TsscStoryServiceTest {
	
	@Mock
	private TsscStoryRepository tsscStoryRepository;
	
	@Mock
	private TsscGameRepository TsscGameRepository;
	
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
		
	}
	
	
}

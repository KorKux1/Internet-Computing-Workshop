package co.edu.icesi.internetcomputing.workshop.integration_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscGameRepository;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscStoryRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;
import co.edu.icesi.internetcomputing.workshop.unit_tests.TsscGameServiceTest;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
public class TsscStoryServiceTest  extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private TsscGameServiceImp tsscGameServiceTest;
	
	@Autowired
	private TsscStoryServiceImp tsscStoryServiceImp;
	
	private TsscGame tsscGame;
	
	private Optional<TsscGame> tsscGames;
	

	
	@BeforeClass
	public void initialize() {
		tsscGame = new TsscGame();
		tsscGame.setId(1);
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setName("Game 1");

		tsscGameServiceTest.save(tsscGame);
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

		assertTrue(tsscStoryServiceImp.save(tsscStory));
		assertNotNull(tsscStoryServiceImp.findById(tsscStory.getId()));
		
	}
	
	/*@Test()
	public void testAddGame2() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(5));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(0));
		tsscStory.setTsscGame(tsscGame);
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
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
	}*/
	
	
}

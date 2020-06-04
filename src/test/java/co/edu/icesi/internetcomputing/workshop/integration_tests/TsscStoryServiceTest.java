package co.edu.icesi.internetcomputing.workshop.integration_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
public class TsscStoryServiceTest  extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private TsscGameServiceImp tsscGameServiceTest;
	
	@Autowired
	private TsscStoryServiceImp tsscStoryServiceImp;
	
	private TsscGame tsscGame;
	
	private TsscStory tsscStoryU;
	
	private TsscGame tsscGameU;
	
	
	@BeforeClass
	public void initialize() {
		tsscGame = new TsscGame();
		tsscGame.setId(1);
		tsscGame.setNGroups(5);
		tsscGame.setNSprints(5);
		tsscGame.setName("Game 1");

		tsscGameServiceTest.save(tsscGame);
	}
	
	
	/*
	 * SAVE TESTS
	 * */
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
	
	@Test(expectedExceptions=NoSuchElementException.class)
	public void testAddStory2() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(5));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(0));
		tsscStory.setTsscGame(tsscGame);
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertNull(tsscStoryServiceImp.findById(tsscStory.getId()));
	}
	
	@Test(expectedExceptions=NoSuchElementException.class)
	public void testAddStory3() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(0));
		tsscStory.setPriority(new BigDecimal(3));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
		

		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertNull(tsscStoryServiceImp.findById(tsscStory.getId()));

	}
	
	@Test(expectedExceptions=NoSuchElementException.class)
	public void testAddStory4() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(3));
		tsscStory.setPriority(new BigDecimal(0));
		tsscStory.setBusinessValue(new BigDecimal(4));
		tsscStory.setTsscGame(tsscGame);
			
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertNull(tsscStoryServiceImp.findById(tsscStory.getId()));

	}
	
	@Test(expectedExceptions=NoSuchElementException.class)
	public void testAddStory5() {
		
		TsscStory tsscStory = new TsscStory();
		tsscStory.setId(0);
		tsscStory.setDescription("Story 1");
		tsscStory.setInitialSprint(new BigDecimal(3));
		tsscStory.setPriority(new BigDecimal(0));
		tsscStory.setBusinessValue(new BigDecimal(4));
		TsscGame tsscGame2 = new TsscGame();
		tsscGame2.setId(2);
		tsscStory.setTsscGame(tsscGame2);
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertNull(tsscStoryServiceImp.findById(tsscStory.getId()));
	}
	
	/*
	 * UPDATED TESTS
	 * */
	
	@BeforeMethod
	private void initUpdates() {
		tsscGameU = new TsscGame();
		tsscGameU.setId(1);
		tsscGameU.setNGroups(5);
		tsscGameU.setNSprints(5);
		tsscGameU.setName("Game 1");		
		tsscGameServiceTest.save(tsscGameU);

		
		tsscStoryU = new TsscStory();
		tsscStoryU.setId(0);
		tsscStoryU.setDescription("Story 1");
		tsscStoryU.setInitialSprint(new BigDecimal(5));
		tsscStoryU.setPriority(new BigDecimal(3));
		tsscStoryU.setBusinessValue(new BigDecimal(4));
		tsscStoryU.setTsscGame(tsscGameU);
		
		tsscStoryServiceImp.save(tsscStoryU);
	}
	
	@Test(groups = "Update")
	public void testUpdateStory1() {
		
		
		TsscStory tsscStory = tsscStoryServiceImp.findById(tsscStoryU.getId());
		tsscStory.setDescription("TsscStory Updated");

		assertTrue(tsscStoryServiceImp.save(tsscStory));
		
		assertEquals(tsscStoryServiceImp.findById(tsscStory.getId()).getDescription(), tsscStory.getDescription());
		
	}

	@Test
	public void testUpdateStory2() {
		TsscStory tsscStory =tsscStoryServiceImp.findById(tsscStoryU.getId());

		tsscStory.setBusinessValue(new BigDecimal(0));
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertEquals(tsscStoryServiceImp.findById(tsscStory.getId()).getDescription(), tsscStory.getDescription());

	}
	
	@Test
	public void testUpdateStory3() {
		
		TsscStory tsscStory =tsscStoryServiceImp.findById(tsscStoryU.getId());

		tsscStory.setInitialSprint(new BigDecimal(0));
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertEquals(tsscStoryServiceImp.findById(tsscStory.getId()).getDescription(), tsscStory.getDescription());


	}
	
	@Test
	public void testUpdateStory4() {
		
		TsscStory tsscStory =tsscStoryServiceImp.findById(tsscStoryU.getId());

		tsscStory.setPriority(new BigDecimal(0));
		
		assertFalse(tsscStoryServiceImp.save(tsscStory));
		assertEquals(tsscStoryServiceImp.findById(tsscStory.getId()).getDescription(), tsscStory.getDescription());

	}
	
}

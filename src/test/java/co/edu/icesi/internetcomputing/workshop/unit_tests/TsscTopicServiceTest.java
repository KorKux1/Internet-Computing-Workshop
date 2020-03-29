package co.edu.icesi.internetcomputing.workshop.unit_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.repositories.TsscTopicRepository;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

@SpringBootTest(classes = SystemManagementActivitiesApplication.class)
public class TsscTopicServiceTest extends AbstractTestNGSpringContextTests  {
	
	@Mock
	private TsscTopicRepository tsscTopicRepository;
	
	@InjectMocks
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	@BeforeMethod(alwaysRun = true)
	public void iniMock() {
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void testaddTopic1() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 1");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(3);
		
		assertTrue(tsscTopicServiceImp.save(tsscTopic));
		//Mockito.verifyNoMoreInteractions(tsscTopicRepository);
	}
	
	@Test
	public void testAddTopic2() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 2");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(0);
		tsscTopic.setDefaultSprints(3);
		tsscTopicServiceImp.save(tsscTopic);
		verify(tsscTopicRepository).save(tsscTopic);
		
		//verifyNoInteractions(tsscTopicRepository);
	}
	
	@Test
	public void testAddTopic3() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setId(0);
		tsscTopic.setDescription("Description");
		tsscTopic.setTsscStories(null);
		tsscTopic.setName("Topic 3");
		tsscTopic.setGroupPrefix("G1");
		tsscTopic.setDefaultGroups(3);
		tsscTopic.setDefaultSprints(0);
		tsscTopicServiceImp.save(tsscTopic);
		verify(tsscTopicRepository).save(tsscTopic);
		
		//verifyNoInteractions(tsscTopicRepository);
	}
	
	/*@Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp="El topic no puede ser Nulo")
	public void testAddTopicNull() {
		
		TsscTopic tsscTopic = null;
		tsscTopicServiceImp.save(tsscTopic);
		verify(tsscTopicRepository).save(tsscTopic);
	//	verifyNoInteractions(tsscTopicRepository);
	}*/
	
}

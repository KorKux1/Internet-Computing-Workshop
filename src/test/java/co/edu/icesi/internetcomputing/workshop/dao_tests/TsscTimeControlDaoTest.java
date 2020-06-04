package co.edu.icesi.internetcomputing.workshop.dao_tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.dao.ITsscTimeControlDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
@Rollback(false)
public class TsscTimeControlDaoTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private ITsscTimeControlDao tsscTimeControlDao;
	
	private TsscTimecontrol tsscTimecontrol;
	
	
	@BeforeMethod
	public void initialize() {
		tsscTimecontrol = new TsscTimecontrol();
		tsscTimecontrol.setName("Time Control");
		tsscTimecontrol.setState("Activo");
		
		
		tsscTimeControlDao.save(tsscTimecontrol);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTimeControl() {
		assertNotNull(tsscTimeControlDao);
		
		TsscTimecontrol tssTimeControlAux = new TsscTimecontrol();
		
		tsscTimecontrol.setName("Time Control");
		tsscTimecontrol.setState("Activo");
		
		try {
			tsscTimeControlDao.save(tssTimeControlAux);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		TsscTimecontrol timeControl = tsscTimeControlDao.findById(tsscTimecontrol.getId());
		
		assertEquals(timeControl.getName(), tsscTimecontrol.getName());
		assertEquals(timeControl.getState(), tsscTimecontrol.getState());

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTimeControl() {
		TsscTimecontrol timeControl = tsscTimeControlDao.findById(tsscTimecontrol.getId());
		
		assertNotNull(timeControl);
		
		timeControl.setName("Time Control");
		timeControl.setState("Activo");
		
		
		tsscTimeControlDao.update(timeControl);
		
		TsscTimecontrol timeControlUpdated = tsscTimeControlDao.findById(tsscTimecontrol.getId());
		
		assertEquals(timeControl.getName(), timeControlUpdated.getName());
		assertEquals(timeControl.getState() ,timeControlUpdated.getState());
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscTimecontrol> timeControls = tsscTimeControlDao.findAll();
		assertTrue(timeControls.size() == 1);
		
		TsscTimecontrol tsscStoryUpdated = new TsscTimecontrol();
		tsscStoryUpdated.setName("Time Control Updated");
		tsscStoryUpdated.setState("Inactivo");

		tsscTimeControlDao.save(tsscStoryUpdated);
		
		timeControls = tsscTimeControlDao.findAll();
		assertTrue(timeControls.size() == 2);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		TsscTimecontrol timeControl = tsscTimeControlDao.findById(tsscTimecontrol.getId());
		assertNotNull(timeControl);
		
		tsscTimeControlDao.delete(timeControl);
		
		List<TsscTimecontrol> admins = tsscTimeControlDao.findAll();
		
		assertTrue(admins.isEmpty());
	}


}
	
	

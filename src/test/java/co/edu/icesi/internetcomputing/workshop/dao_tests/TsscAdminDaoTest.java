package co.edu.icesi.internetcomputing.workshop.dao_tests;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import co.edu.icesi.internetcomputing.workshop.SystemManagementActivitiesApplication;
import co.edu.icesi.internetcomputing.workshop.dao.ITsscAdminDao;
import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;

@SpringBootTest(classes=SystemManagementActivitiesApplication.class)
//@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TsscAdminDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ITsscAdminDao tsscAdminDao;
	
	private TsscAdmin tsscAdmin;
	
	@BeforeClass
	public void initialize() {
		tsscAdmin = new TsscAdmin();
		tsscAdmin.setPassword("password");
		tsscAdmin.setSuperAdmin("SUPER_ADMIN");
		tsscAdmin.setUsername("user");
		tsscAdminDao.save(tsscAdmin);
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveAdmin() {
		assertNotNull(tsscAdminDao);
		
		TsscAdmin tsscAdmin = new TsscAdmin();
		tsscAdmin.setSuperAdmin("SUPER_ADMIN");
		tsscAdmin.setPassword("123456");
		tsscAdmin.setUsername("Test");
		try {
			tsscAdminDao.save(tsscAdmin);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateAdmin() {
		TsscAdmin tsscAdminUpdated = new TsscAdmin();
		tsscAdminUpdated.setPassword("passwordUpdated");
		tsscAdminUpdated.setUsername("UserUpdated");
		tsscAdminUpdated.setSuperAdmin("SUPER_ADMIN");
		tsscAdminDao.update(tsscAdminUpdated);
		assertNotNull(tsscAdminDao);
		
		List<TsscAdmin> tsscAdminList = tsscAdminDao.findByUsername("UserUpdated");
		assertThat(tsscAdminList.size() >= 1);
		
		if (tsscAdminList.size() > 0) {
			TsscAdmin tsscAdminFound = tsscAdminList.get(0);
			assertNotNull(tsscAdminFound);
			assertEquals(tsscAdminFound.getUsername(), "UserUpdated");
			assertEquals(tsscAdminFound.getPassword(), "passwordUpdated");
			assertEquals(tsscAdminFound.getSuperAdmin(), "SUPER_ADMIN");
		} else {
			fail();
		}
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByUserName() {
		TsscAdmin admin = tsscAdminDao.findByUsername(tsscAdmin.getUsername()).get(0);
		assertEquals(admin.getUsername(), tsscAdmin.getUsername());
		assertEquals(admin.getPassword(), tsscAdmin.getPassword());
		assertEquals(admin.getSuperAdmin(), tsscAdmin.getSuperAdmin());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscAdmin> admins = tsscAdminDao.findAll();
		assertTrue(admins.size() == 1);
		
		TsscAdmin tsscAdminUpdated = new TsscAdmin();
		tsscAdminUpdated.setSuperAdmin("SUPER_ADMIN");
		tsscAdminUpdated.setPassword("123456");
		tsscAdminUpdated.setUsername("Test");
		
		tsscAdminDao.save(tsscAdminUpdated);
		
		admins = tsscAdminDao.findAll();
		assertTrue(admins.size() == 2);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateDelete() {
		TsscAdmin admin = tsscAdminDao.findByUsername(tsscAdmin.getUsername()).get(0);
		assertNotNull(admin);
		
		tsscAdminDao.delete(admin);
		
		List<TsscAdmin> admins = tsscAdminDao.findByUsername(tsscAdmin.getUsername());
		
		assertTrue(admins.isEmpty());
	}
	
	
}

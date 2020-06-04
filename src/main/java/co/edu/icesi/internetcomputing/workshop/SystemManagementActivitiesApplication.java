package co.edu.icesi.internetcomputing.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.internetcomputing.workshop.model.TsscAdmin;
import co.edu.icesi.internetcomputing.workshop.services.TsscAdminServiceImp;

@SpringBootApplication
public class SystemManagementActivitiesApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext contex = SpringApplication.run(SystemManagementActivitiesApplication.class, args);
		TsscAdminServiceImp tsscAdminServiceImp = contex.getBean(TsscAdminServiceImp.class);
		TsscAdmin tsscAdmin = new TsscAdmin();
		tsscAdmin.setUsername("KorKux");
		tsscAdmin.setPassword("{noop}1234");
		tsscAdmin.setSuperAdmin("SUPER_ADMIN");
		tsscAdminServiceImp.save(tsscAdmin);
		
		TsscAdmin tsscAdmin2 = new TsscAdmin();
		tsscAdmin2.setUsername("KorKux1");
		tsscAdmin2.setPassword("{noop}1234");
		tsscAdmin2.setSuperAdmin("ADMIN");
		tsscAdminServiceImp.save(tsscAdmin2);
		
	}

}

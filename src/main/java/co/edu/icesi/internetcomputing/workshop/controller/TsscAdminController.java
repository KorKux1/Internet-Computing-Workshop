package co.edu.icesi.internetcomputing.workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.internetcomputing.workshop.services.TsscAdminServiceImp;

@Controller
public class TsscAdminController {
	
	@Autowired
	TsscAdminServiceImp tsscAdminServiceImp;
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	
}

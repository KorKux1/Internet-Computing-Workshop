package co.edu.icesi.internetcomputing.workshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.internetcomputing.workshop.model.TsscGame;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame.GameValidator;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

@Controller
public class TsscGameController {
	
	@Autowired
	private TsscGameServiceImp tsscGameServiceImp;
	
	@Autowired
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	@GetMapping("/games")
	public String index(Model model) {
		model.addAttribute("games", tsscGameServiceImp.findAll());
		return "games/index";
	}
	
	@GetMapping("/game/add")
	public String add(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", tsscGameServiceImp.findAll());
		return "games/add";
	}
	
	@PostMapping("/game/add")
	public String saveGame(@Validated(GameValidator.class) TsscGame tsscGame, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getAdminPassword());
		model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
		model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
		model.addAttribute("nGroups", tsscGame.getNGroups());
		model.addAttribute("nSprints", tsscGame.getNSprints());
		model.addAttribute("userPassword", tsscGame.getUserPassword());
		model.addAttribute("guestPassword", tsscGame.getGuestPassword());
		model.addAttribute("topics", tsscGameServiceImp.findAll());
		
		if (action.equals("Cancelar")) {
			return "redirect:/games/";
		}
		
		if (bindingResult.hasErrors()) {
			return "games/add";
		}
		
		if (tsscGame.getTsscTopic() == null) {
			tsscGameServiceImp.save(tsscGame);
		}
		else {
			tsscGameServiceImp.save2(tsscGame);
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/{id}/stories")
	public String indexStories(@PathVariable("id") long id, Model model) {
		TsscGame tsscGame = tsscGameServiceImp.findById(id);
		if(tsscGame == null) {
			throw new IllegalArgumentException("Id Invalido:" + id);
		}
		model.addAttribute("stories", tsscGame.getTsscStories());
		
		return "stories/index";
	}
	
	@GetMapping("/games/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscGame tsscGame = tsscGameServiceImp.findById(id);
		if (tsscGame == null) {
			throw new IllegalArgumentException("Id Incorrecto:" + id);
		}
		model.addAttribute("tsscGame", tsscGame);
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getAdminPassword());
		model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
		model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
		model.addAttribute("nGroups", tsscGame.getNGroups());
		model.addAttribute("nSprints", tsscGame.getNSprints());
		model.addAttribute("userPassword", tsscGame.getUserPassword());
		model.addAttribute("guestPassword", tsscGame.getGuestPassword());
		model.addAttribute("topics", tsscTopicServiceImp.findAll());

		return "games/edit";	
	}
	
	@PostMapping("/games/edit/{id}")
	public String editGame(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action,@Validated(GameValidator.class) TsscGame tsscGame, BindingResult bindingResult, Model model) {
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getAdminPassword());
		model.addAttribute("scheduledDate", tsscGame.getScheduledDate());
		model.addAttribute("scheduledTime", tsscGame.getScheduledTime());
		model.addAttribute("nGroups", tsscGame.getNGroups());
		model.addAttribute("nSprints", tsscGame.getNSprints());
		model.addAttribute("userPassword", tsscGame.getUserPassword());
		model.addAttribute("guestPassword", tsscGame.getGuestPassword());
		model.addAttribute("topics", tsscTopicServiceImp.findAll());
		
		
		if (action.equals("Cancelar")) {
			return "redirect:/games/";
		}
		
		if (bindingResult.hasErrors()) {
			return "games/edit";
		}	
		
		if (tsscGame.getTsscTopic() == null) {
			tsscGameServiceImp.save(tsscGame);
		}
		else {
			tsscGameServiceImp.save2(tsscGame);
		}
		return "redirect:/games/";
	}

}

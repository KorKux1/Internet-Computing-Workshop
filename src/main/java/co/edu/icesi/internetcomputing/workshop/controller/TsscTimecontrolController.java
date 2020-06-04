package co.edu.icesi.internetcomputing.workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.internetcomputing.workshop.model.TsscTimecontrol;
import co.edu.icesi.internetcomputing.workshop.model.TsscGame.GameValidator;
import co.edu.icesi.internetcomputing.workshop.services.TsscTimeControlServiceImp;

@Controller
public class TsscTimecontrolController {
	
	@Autowired
	private TsscTimeControlServiceImp tsscTimeControlServiceImp;
	
	@GetMapping("/game/timecontrol/{id}")
	public String timecontrol(@PathVariable("id") long id, Model model) {
		TsscTimecontrol times = tsscTimeControlServiceImp.findById(id);
		model.addAttribute("times", times);
		return "games/timecontrol";
	}
	
	@GetMapping("/timecontrol/add")
	public String addtimecontrol(Model model) {
		model.addAttribute("tsscGame", new TsscTimecontrol());
		model.addAttribute("topics", tsscTimeControlServiceImp.findAll());
		return "timecontrol/add";
	}
	
	@PostMapping("/timecontrol/add")
	public String savetimecontrol(TsscTimecontrol tsscGame, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getName());
		model.addAttribute("scheduledDate", tsscGame.getState());
		model.addAttribute("scheduledTime", tsscGame.getType());
		model.addAttribute("nGroups", tsscGame.getAutostart());
		model.addAttribute("topics", tsscTimeControlServiceImp.findAll());
		
		if (action.equals("Cancelar")) {
			return "redirect:/game/timecontrol/1";
		}
		
		if (bindingResult.hasErrors()) {
			return "timecontrol/add";
		}
		
			tsscTimeControlServiceImp.save(tsscGame);
		return "redirect:/game/timecontrol/"+tsscGame.getId();
	}
	
	@GetMapping("/timecontrol/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscTimecontrol tsscGame = tsscTimeControlServiceImp.findById(id);
		if (tsscGame == null) {
			throw new IllegalArgumentException("Id Incorrecto:" + id);
		}
		model.addAttribute("tsscGame", tsscGame);
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getName());
		model.addAttribute("scheduledDate", tsscGame.getState());
		model.addAttribute("scheduledTime", tsscGame.getType());
		model.addAttribute("nGroups", tsscGame.getAutostart());
		model.addAttribute("topics", tsscTimeControlServiceImp.findAll());

		return "timecontrol/edit";	
	}
	
	@PostMapping("/timecontrol/edit/{id}")
	public String editGame(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action, TsscTimecontrol tsscGame, BindingResult bindingResult, Model model) {
		model.addAttribute("name", tsscGame.getName());
		model.addAttribute("adminPassword", tsscGame.getName());
		model.addAttribute("scheduledDate", tsscGame.getState());
		model.addAttribute("scheduledTime", tsscGame.getType());
		model.addAttribute("nGroups", tsscGame.getAutostart());
		model.addAttribute("topics", tsscTimeControlServiceImp.findAll());
		
		
		if (action.equals("Cancelar")) {
			return "redirect:/game/timecontrol/"+tsscGame.getId();
		}
		
		if (bindingResult.hasErrors()) {
			return "timecontrol/edit";
		}	
		
		if (tsscGame.getTsscTopic() == null) {
			tsscTimeControlServiceImp.update(tsscGame);
		}
		return "redirect:/game/timecontrol/"+tsscGame.getId();
	}
	
	@GetMapping("/timecontrol/delete/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		tsscTimeControlServiceImp.delete(id);
		return "redirect:/games/";
	}
	
}

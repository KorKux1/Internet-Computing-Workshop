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

import co.edu.icesi.internetcomputing.workshop.model.TsscTopic;
import co.edu.icesi.internetcomputing.workshop.model.TsscTopic.TopicValidator;
import co.edu.icesi.internetcomputing.workshop.services.TsscTopicServiceImp;

@Controller
public class TsscTopicController {
	
	@Autowired
	private TsscTopicServiceImp tsscTopicServiceImp;
	
	@GetMapping("/topics")
	public String index(Model model) {
		model.addAttribute("topics", tsscTopicServiceImp.findAll());
		return "topics/index";
	}
	
	@GetMapping("/topic/add")
	public String add(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topics/add";
	}
	
	@PostMapping("/topic/add")
	public String saveTopic(@Validated(TopicValidator.class) TsscTopic tsscTopic, BindingResult bidBindingResult, @RequestParam(value="action", required = true) String action, Model model) {
		model.addAttribute("name", tsscTopic.getName());
		model.addAttribute("description", tsscTopic.getDescription());
		model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
		model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
		model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
		
		if (action.equals("Cancelar")) {
			return "redirect:/topics/";
		}
		
		if (bidBindingResult.hasErrors()) {
			return "topics/add";
		}
		if (!action.equals("Cancelar")) {
			tsscTopicServiceImp.save(tsscTopic);
		}
		return "redirect:/topics/";
	}
	
	@GetMapping("/topics/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscTopic tsscTopic = tsscTopicServiceImp.findById(id);
		
		if (tsscTopic == null) {
			throw new IllegalArgumentException("Id incorrecto: " + id);
		}
	
		model.addAttribute("tsscTopic", tsscTopic);
		model.addAttribute("name", tsscTopic.getName());
		model.addAttribute("description", tsscTopic.getDescription());
		model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
		model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
		model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
		return "topics/edit";
	}
	
	@PostMapping("/topics/edit/{id}")
	public String editTopic(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action,@Validated(TopicValidator.class) TsscTopic tsscTopic, BindingResult bindingResult, Model model) {
		model.addAttribute("name", tsscTopic.getName());
		model.addAttribute("description", tsscTopic.getDescription());
		model.addAttribute("defaultGroups", tsscTopic.getDefaultGroups());
		model.addAttribute("defaultSprints", tsscTopic.getDefaultSprints());
		model.addAttribute("groupPrefix", tsscTopic.getGroupPrefix());
		
		if (action.equals("Cancelar")) {
			return "redirect:/topics/";
		}
		
		if (bindingResult.hasErrors()) {
			return "topics/edit";
		}
		if (!action.equals("Cancelar")) {
			tsscTopicServiceImp.save(tsscTopic);
		}
		
		return "redirect:/topics/";
	}
}

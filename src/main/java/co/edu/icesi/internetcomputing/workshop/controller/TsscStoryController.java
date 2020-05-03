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

import co.edu.icesi.internetcomputing.workshop.model.TsscStory;
import co.edu.icesi.internetcomputing.workshop.model.TsscStory.StoryValidator;
import co.edu.icesi.internetcomputing.workshop.services.TsscGameServiceImp;
import co.edu.icesi.internetcomputing.workshop.services.TsscStoryServiceImp;

@Controller
public class TsscStoryController {
	
	@Autowired
	private TsscStoryServiceImp tsscStoryServiceImp;
	
	@Autowired
	private TsscGameServiceImp tsscGameServiceImp;
	
	@GetMapping("/stories")
	public String index(Model model) {
		model.addAttribute("stories", tsscStoryServiceImp.findAll());
		return "stories/index";
	}
	
	@GetMapping("/story/add")
	public String add(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", tsscGameServiceImp.findAll());
		return "stories/add";
	}
	
	@PostMapping("/story/add")
	public String saveStory(@Validated(StoryValidator.class) TsscStory tsscStory, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {
		model.addAttribute("description", tsscStory.getDescription());
		model.addAttribute("businessValue", tsscStory.getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.getInitialSprint());
		model.addAttribute("priority", tsscStory.getPriority());
		model.addAttribute("games", tsscGameServiceImp.findAll());
		
		if (action.equals("Cancelar")) {
			return "redirect:/stories/";
		}
		
		if (bindingResult.hasErrors()) {
			return "stories/add";
		}
		
		tsscStoryServiceImp.save(tsscStory);
			
		return "redirect:/stories/";
	}
	
	@GetMapping("/stories/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {	
		TsscStory tsscStory = tsscStoryServiceImp.findById(id);
		if (tsscStory == null) {
			throw new IllegalArgumentException("Id Incorrecto" + id);
		}
		
		model.addAttribute("tsscStory", tsscStory);
		model.addAttribute("description", tsscStory.getDescription());
		model.addAttribute("businessValue", tsscStory.getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.getInitialSprint());
		model.addAttribute("priority", tsscStory.getPriority());
		model.addAttribute("games", tsscGameServiceImp.findAll());
		
		
		return "stories/edit";	
	}
	
	@PostMapping("stories/edit/{id}")
	public String updateStory(@PathVariable("id") long id,@RequestParam(value = "action", required = true) String action, @Validated(StoryValidator.class) TsscStory tsscStory, BindingResult bindingResult, Model model) {
		model.addAttribute("games", tsscGameServiceImp.findAll());
		model.addAttribute("description", tsscStory.getDescription());
		model.addAttribute("businessValue", tsscStory.getBusinessValue());
		model.addAttribute("initialSprint", tsscStory.getInitialSprint());
		model.addAttribute("priority", tsscStory.getPriority());
		
		if (action.equals("Cancelar")) {
			return "redirect:/stories/";
		}
		
		if (bindingResult.hasErrors()) {
			return "stories/edit";
		}
		
		tsscStoryServiceImp.save(tsscStory);
			
		return "redirect:/stories/";
	}

}

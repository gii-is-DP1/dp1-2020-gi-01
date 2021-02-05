package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.forms.ActorForm;
import com.project.TabernasSevilla.service.ActorService;

@Controller
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@GetMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("actor", actorService.getPrincipal());
		return "actor/view";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		return createEditModel(model);
	}
	
	@PostMapping("/save")
	public String save(Model model, @ModelAttribute @Valid final ActorForm form, BindingResult binding) {
		if (binding.hasErrors()) {
			model.addAttribute("actor",form);
			return "actor/edit";
		} else {
			try {
				Actor actor = this.actorService.parseForm(form);
				this.actorService.save(actor);
				return "redirect:profile";
			} catch (final Exception e) {
				return this.createEditModel(model, e.getMessage());
			}
		}
	}
	
	private String createEditModel(Model model) {
		return this.createEditModel(model, null);
	}

	private String createEditModel(Model model, String message) {
		Actor actor = this.actorService.getPrincipal();
		ActorForm form = this.actorService.formatForm(actor);
		model.addAttribute("actor", form);
		model.addAttribute("message", message);
		return "actor/edit";
	}
}

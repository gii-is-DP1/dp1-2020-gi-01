package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.TabernasSevilla.forms.ContactForm;
import com.project.TabernasSevilla.service.ContactService;

@Controller
@RequestMapping("/contact")
public class ContactController {
	
	@Autowired
	private ContactService conSer;
	
	@RequestMapping(value="/init", method=RequestMethod.GET)
	 public String createJoba(Model model) {
		final ContactForm confor = new ContactForm();
		model.addAttribute("contactForm", confor);
			return "contact";
	    }
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveJoba(@ModelAttribute @Valid final ContactForm confor, final BindingResult binding, Model model) {
		if(binding.hasErrors()) {
			model.addAttribute("contactForm", confor);
			return this.createJobaEditModel(confor, model);
		}else {
			try {
				this.conSer.register(confor);
				return "redirect:/index";
			}catch(final Exception e) {
				return this.createJobaEditModel(confor, model, e.getMessage());
			}
		}
	}

	private String createJobaEditModel(final ContactForm confor, Model model) {
		return this.createJobaEditModel(confor, model, null);
	}

	private String createJobaEditModel(ContactForm confor, Model model, String message) {
		model.addAttribute(confor);
		model.addAttribute("message", message);
		return "contact";
	}
}

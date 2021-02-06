package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TabernasSevilla.forms.ContactForm;
import com.project.TabernasSevilla.service.ContactService;

//Job Application controller 
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
	//the CV will be stored in /resources/JobApplication
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveJoba(@ModelAttribute @Valid final ContactForm confor, final BindingResult binding, Model model, RedirectAttributes reA) {
		if(binding.hasErrors() || confor.getCv().getSize()==0) {
			model.addAttribute("contactForm", confor);
			return this.createJobaEditModel(confor, model, "");
		}else {
			try {
				this.conSer.register(confor);
				reA.addFlashAttribute("message", "Thanks for sending us the job application"); //si pongo model.addAttribute no funciona, porque no esta pensado para redirecciones
				return "redirect:/index";
			}catch(final Exception e) {
				return this.createJobaEditModel(confor, model, e.getMessage());
			}
		}
	}
	
	

	private String createJobaEditModel(ContactForm confor, Model model, String message) {
		model.addAttribute(confor);
		if(confor.getCv().getSize()==0) {
			message = message + " Please upload your CV";
		}
		model.addAttribute("message", message);
		return "contact";
	}
}

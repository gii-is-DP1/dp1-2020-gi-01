package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.service.CustomerService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	//TODO: Register controller: init & save
	@Autowired
	private CustomerService customerSer;
	
	@RequestMapping(value="/init",method=RequestMethod.GET)
	public String createCustomer(Model model) {
		final RegisterForm regForm = new RegisterForm();
		regForm.setRole("CUSTOMER");
		model.addAttribute("registerForm",regForm);
		return "register";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveUser(@ModelAttribute @Valid final RegisterForm regForm, final BindingResult binding, Model model) {
		if(binding.hasErrors()) {
			model.addAttribute("registerForm",regForm);
			return this.createRegisterEditModel(regForm, model);
		}else {
			try {
				this.customerSer.register(regForm);
				return "redirect:/login";
			} catch (final Exception e) {
				return this.createRegisterEditModel(regForm, model, e.getMessage());
			}
		}
	}
	
	//AUX
	protected String createRegisterEditModel(final RegisterForm regForm, Model model) {
		return this.createRegisterEditModel(regForm,model,null);
	}
	
	protected String createRegisterEditModel(final RegisterForm regForm, Model model, String message) {
		model.addAttribute(regForm);
		model.addAttribute("message",message);
		return "register";
	}
}

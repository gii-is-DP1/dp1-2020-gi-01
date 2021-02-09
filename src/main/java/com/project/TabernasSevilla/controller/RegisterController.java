package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.RegKeyService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private ActorService actorService;
	@Autowired
	private RegKeyService regKeyService;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String createCustomer(@RequestParam(required = false) String key, Model model) {
		final RegisterForm regForm = new RegisterForm();
		if (key != null) {
			if (this.regKeyService.checkKey(key)) {
				regForm.setKey(key);
			} else {
				regForm.setKey("");
			}
		}
		model.addAttribute("registerForm", regForm);
		return "register";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute @Valid final RegisterForm regForm, final BindingResult binding,
			Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("registerForm", regForm);
			return this.createRegisterEditModel(regForm, model);
		} else {
			try {
				this.actorService.register(regForm);
				return "redirect:/login";
			} catch (final Exception e) {
				return this.createRegisterEditModel(regForm, model, e.getMessage());
			}
		}
	}

	// go to employee enter regkey view
	@GetMapping("/employees")
	public String employeeRegister() {
		return "admin/employees/key";
	}

	// AUX
	protected String createRegisterEditModel(final RegisterForm regForm, Model model) {
		return this.createRegisterEditModel(regForm, model, null);
	}

	protected String createRegisterEditModel(final RegisterForm regForm, Model model, String message) {
		model.addAttribute(regForm);
		model.addAttribute("message", message);
		return "register";
	}
	
	@GetMapping("/check-key")
	public @ResponseBody String checkKey(@RequestParam("key") String key) {
		String response = this.regKeyService.checkKey(key).toString();
		return response;
	}
}

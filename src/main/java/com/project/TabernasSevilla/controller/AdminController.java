package com.project.TabernasSevilla.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.TabernasSevilla.domain.RegKey;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.service.RegKeyService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RegKeyService regKeyService;
	@Autowired
	private AuthorityService authService;
	
	// got to control panel
	@GetMapping("/control")
	public String controlPanel() {
		return "admin/controlpanel";
	}

	// return form to create employee register key
	@GetMapping("/employees/key/init")
	public String createKey(Model model) {
		RegKey regKey = this.regKeyService.create();
		List<Authority> auths = this.authService.findAll();
		model.addAttribute("regkey", regKey);
		model.addAttribute("authorities", auths);
		return "admin/employees/key";
	}

	// save register key
	@PostMapping("/employees/key/save")
	public String saveKey(@ModelAttribute @Valid final RegKey regKey, final BindingResult binding, Model model) {
		if(binding.hasErrors()) {
			model.addAttribute("registerForm",regKey);
			return this.createKeyEditModel(regKey, model);
		}else {
			try {
				this.regKeyService.save(regKey);
				return "redirect:/control";
			} catch (final Exception e) {
				return this.createKeyEditModel(regKey, model, e.getMessage());
			}
		}
	}

	// AUX -------------------------------------------------------------------------------------------
	protected String createKeyEditModel(final RegKey regKey, Model model) {
		return this.createKeyEditModel(regKey, model, null);
	}

	protected String createKeyEditModel(final RegKey regKey, Model model, String message) {
		model.addAttribute(regKey);
		model.addAttribute("message", message);
		return "register";
	}

}

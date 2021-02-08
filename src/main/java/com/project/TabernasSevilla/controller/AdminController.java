package com.project.TabernasSevilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	
	@GetMapping("/employees/key")
	public String createKey() {
		return "admin/employees/keys";
	}

	//TODO: TRY CATCH FOR ERRORS
	// create manager key
	@GetMapping("/employees/key/manager")
	public String createManagerKey(Model model) {
		RegKey regKey = this.regKeyService.create();
		Authority auth = this.authService.findByName("MANAGER");
		regKey.setAuthority(auth);
		RegKey saved = this.regKeyService.save(regKey);
		model.addAttribute("regKey",saved);
		return "admin/employees/key";
	}

	// create cook key
	@GetMapping("/employees/key/cook")
	public String createCookKey(Model model) {
		RegKey regKey = this.regKeyService.create();
		Authority auth = this.authService.findByName("COOK");
		regKey.setAuthority(auth);
		RegKey saved = this.regKeyService.save(regKey);
		model.addAttribute("regKey",saved);
		return "admin/employees/key";
	}

	// create waiter key
	@GetMapping("/employees/key/waiter")
	public String createWaiterKey(Model model) {
		RegKey regKey = this.regKeyService.create();
		Authority auth = this.authService.findByName("WAITER");
		regKey.setAuthority(auth);
		RegKey saved = this.regKeyService.save(regKey);
		model.addAttribute("regKey",saved);
		return "admin/employees/key";
	}

	// AUX
	// -------------------------------------------------------------------------------------------
	protected String createKeyEditModel(final RegKey regKey, Model model) {
		return this.createKeyEditModel(regKey, model, null);
	}

	protected String createKeyEditModel(final RegKey regKey, Model model, String message) {
		model.addAttribute(regKey);
		model.addAttribute("message", message);
		return "register";
	}

}

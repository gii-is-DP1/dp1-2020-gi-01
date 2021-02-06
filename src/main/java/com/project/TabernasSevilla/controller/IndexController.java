package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


	@RequestMapping({ "/", "/index", "/inicio" })
	public String mainView(Model model) {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, Model model) {
		if (error == true) {
			// Assign an error message
			model.addAttribute("error", "You have entered an invalid username or password!");
		} else {
			model.addAttribute("error", "");
		}
		return "login";
	}

	@RequestMapping(value = "/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}

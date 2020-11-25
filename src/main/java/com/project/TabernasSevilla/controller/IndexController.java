package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"/","/index","/inicio"})
    public String mainView(Model model) {
		return "index";
    }
	

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

}


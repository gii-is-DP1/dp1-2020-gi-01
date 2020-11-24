package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.view.ThymeleafView;

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


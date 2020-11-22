package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@RequestMapping({"/","/index","/inicio"})
    public String hello(@RequestParam(value = "name", defaultValue = "Carlos, Adrian H., Esteban, Adil, Juan y José",
        required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
	
	@RequestMapping({"/login"})
    public String login(Model model) {
        return "login";
    }
	@PostMapping({"/logcheck"})
		public String logCheck(String username, String password, Model model)
		{
		//model.addAttribute("username", username);
		//model.addAttribute("password", password);
		if(username.equals("admin") && password.equals("admin")) 
		{
			model.addAttribute("phrase", "Hi, " + username);
			return "index";
		}else
		{
			model.addAttribute("loginErr", "[!!] Bad credentials");
			return "login";
		}
		
		}
	
	@RequestMapping({"/register"})
    public String register(@RequestParam(value = "name", defaultValue = "persona registrada. ¿Cómo estás?",
    	required = true) String name, Model model) {
		model.addAttribute("name", name);
        return "index";
    }
}


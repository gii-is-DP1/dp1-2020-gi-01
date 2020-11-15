package com.project.TabernasSevilla.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping({"/register"})
    public String register(@RequestParam(value = "name", defaultValue = "persona registrada. ¿Cómo estás?",
    	required = true) String name, Model model) {
		model.addAttribute("name", name);
        return "index";
    }
}


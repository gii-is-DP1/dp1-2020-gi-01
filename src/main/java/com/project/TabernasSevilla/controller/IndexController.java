package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.view.ThymeleafView;

@Controller
public class IndexController {

	@RequestMapping({"/","/index","/inicio"})
    public String mainView(Model model) {
		model.addAttribute("message", "Test");
		return "index";
    }
	
//	@RequestMapping({"/login"})
//    public String login(String username, String password, String _csrf, Model model) {
//		model.addAttribute("username", username);
//		model.addAttribute("password", password);
//		model.addAttribute("_csrf", _csrf);
//        return "login";
//    }
	
//	@RequestMapping({"/login"})
//	public String login(String username, String password, Model model) {
//      return "login";
//  }
	
	/*
	@RequestMapping({"/admin"})
	public String admin(String username, Model model) {
		model.addAttribute("username", username);
      return "admin";
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
    */
}


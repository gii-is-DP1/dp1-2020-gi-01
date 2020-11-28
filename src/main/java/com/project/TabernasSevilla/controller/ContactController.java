package com.project.TabernasSevilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ContactController {
	
	@RequestMapping("/contact")
	 public String mainView(Model model) {
			return "contact";
	    }

}

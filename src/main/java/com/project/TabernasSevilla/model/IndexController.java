package com.project.TabernasSevilla.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@GetMapping({"/","/index","/inicio"})
    public String hello(@RequestParam(value = "name", defaultValue = "Carlos, Adrian H., Esteban, Adil, Juan y José",
        required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}

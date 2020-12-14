package com.project.TabernasSevilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/setPreferred")
	public String viewLocation (@RequestParam(required=true) final Integer id, Model model) {
		this.customerService.setPreferredEstablishment(id);

		return "redirect:/location/view?id="+Integer.toString(id);
	}

}

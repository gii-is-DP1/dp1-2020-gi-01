package com.project.TabernasSevilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@ControllerAdvice
public class AbstractController {

	@Autowired
	private UserService service;
	
	
	public String checkIfCurrentUserIsAllowed(String view, String authority) {

		try {
			User user = service.getPrincipal();
			assert user != null && user.getAuthorities().contains(new Authority(authority));
			return view;
		} catch(Exception e) {
			return "error";
		}
		
		
	}
	
	
}

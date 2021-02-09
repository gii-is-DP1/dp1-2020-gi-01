package com.project.TabernasSevilla.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@ControllerAdvice
public class UsErrorController{

		@ExceptionHandler(Throwable.class)
		@RequestMapping("/error")
	    public String handleError(final Throwable throwable, final Model model) {
			String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
	        model.addAttribute("errorMessage", errorMessage);
	        return "error";
	    }

		

}
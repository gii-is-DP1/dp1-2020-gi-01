package com.project.TabernasSevilla.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
//public class ErrorController {
//
//    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);
//
//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    public String exception(final Throwable throwable, final Model model) {
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//        model.addAttribute("errorMessage", errorMessage);
//        return "error";
//    }
//    
//
//}

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
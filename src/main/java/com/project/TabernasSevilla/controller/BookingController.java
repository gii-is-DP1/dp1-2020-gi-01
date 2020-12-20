package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.TabernasSevilla.forms.BookingForm;

import com.project.TabernasSevilla.service.BookingService;

@Controller
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookService;
	
	@RequestMapping(value="/init", method=RequestMethod.GET)
	 public String createBooking(Model model) {
		final BookingForm bookform = new BookingForm();
		model.addAttribute("bookingForm", bookform);
			return "bookingView";
	    }
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveBooking(@ModelAttribute @Valid final BookingForm bookform, final BindingResult binding, Model model) {
		if(binding.hasErrors()) {
			model.addAttribute("bookingForm", bookform);
			return this.createBookingEditModel(bookform, model);
		}else {
			try {
				this.bookService.register(bookform);
				return "redirect:/index";
			}catch(final Exception e) {
				return this.createBookingEditModel(bookform, model, e.getMessage());
			}
		}
	}
	
	private String createBookingEditModel(final BookingForm bookform, Model model) {
		return this.createBookingEditModel(bookform, model, null);
	}
	
	private String createBookingEditModel(BookingForm bookform, Model model, String message) {
		model.addAttribute(bookform);
		model.addAttribute("message", message);
		return "bookingView";
	}
}

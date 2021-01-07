package com.project.TabernasSevilla.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.forms.BookingForm;
import com.project.TabernasSevilla.service.BookingService;
import com.project.TabernasSevilla.service.EstablishmentService;

@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookService;
	@Autowired
	private EstablishmentService establishmentService;

	@RequestMapping(value = "/init/{id}", method = RequestMethod.GET)
	public String createBooking(@PathVariable("id") int establishmentId, Model model) {
		Establishment est = this.establishmentService.findById(establishmentId);
		Booking booking = this.bookService.initialize(est);
		
	
		model.addAttribute("establishment", booking.getEstablishment());
		model.addAttribute("booking", booking);
		return "booking/edit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBooking(@ModelAttribute @Valid final Booking booking, final BindingResult binding,
			Model model) {

		if (binding.hasErrors()) {
			model.addAttribute("booking", booking);
			return this.createBookingEditModel(booking, model);
		} else {
			try {
				this.bookService.register(booking);
				return "redirect:/index";
			} catch (final Exception e) {
				return this.createBookingEditModel(booking, model, e.getMessage());
			}
		}
	}

	private String createBookingEditModel(final Booking booking, Model model) {
		return this.createBookingEditModel(booking, model, null);
	}

	private String createBookingEditModel(Booking booking, Model model, String message) {
		model.addAttribute(booking);
		model.addAttribute("establishment", booking.getEstablishment());
		model.addAttribute("message", message);
		return "booking/edit";
	}
}

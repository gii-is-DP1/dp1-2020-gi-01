package com.project.TabernasSevilla.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;
import com.project.TabernasSevilla.service.BookingService;
import com.project.TabernasSevilla.service.EstablishmentService;
import com.project.TabernasSevilla.service.TableService;

@Controller
@RequestMapping("/table")
public class TableController {

	@Autowired
	private TableService tableService;
	@Autowired
	private EstablishmentService establishmentService;
	@Autowired
	private BookingService bookingService;

	@GetMapping("/establishment/{id}")
	public String manageTables(@PathVariable("id") int establishmentId, Model model) {
		Establishment est = this.establishmentService.findById(establishmentId);
		List<RestaurantTable> tables = this.tableService.findByEstablishment(est);
		for(RestaurantTable t: tables) {
			t.getBooking();
		}
		Long occupied = this.tableService.getOccupancyAtRestaurant(est);
		Long capacity = this.tableService.getCapacityAtRestaurant(est);
		Long freeTables = this.tableService.countFreeTables(est);
		String estimate = this.tableService.estimateFreeTable(est);
		List<Booking> bookings = this.bookingService.findUnallocatedByEstablishment(est);
		model.addAttribute("bookings", bookings);
		model.addAttribute("estimate", estimate);
		model.addAttribute("totalTables", tables.size());
		model.addAttribute("freeTables", freeTables);
		model.addAttribute("capacity", capacity);
		model.addAttribute("occupied", occupied);
		model.addAttribute("establishment", est);
		model.addAttribute("tables", tables);
		return "table/list";
	}

	// create table
	@GetMapping("/establishment/{id}/add")
	public String addTable(@PathVariable("id") int establishmentId, Model model) {
		Establishment est = this.establishmentService.findById(establishmentId);
		this.tableService.quickCreate(est, 1);

		return "redirect:/table/establishment/" + establishmentId ;
	}

	// delete table
	@GetMapping("/{tableId}/delete")
	public String deleteTable(@PathVariable("tableId") int tableId, Model model) {
		RestaurantTable table = this.tableService.findById(tableId);
		Establishment est = table.getEstablishment();
		this.tableService.delete(table);

		return "redirect:/table/establishment/" + est.getId() ;
	}

	// modify table
	@GetMapping("/{tableId}/modify")
	public String modifyTable(@PathVariable("tableId") int tableId, Model model,@RequestParam(required=false) Integer bookingId,@RequestParam(required=false) final Integer num,@RequestParam(required=true) final Integer cap, @RequestParam(required=false) final Integer oc) {
		RestaurantTable table = this.tableService.findById(tableId);
		table.setSeating(cap);
		if(oc !=null) {
			table.setOccupied(oc);
		}
		if(num!=null) {
			table.setNumber(num);
		}
		if(cap!=null) {
			table.setSeating(cap);
		}
		if(bookingId!=null) {
			Booking booking = this.bookingService.findById(bookingId).get();
			table.setBooking(booking);
		}else {
			table.setBooking(null);
		}
		this.tableService.save(table);
		Establishment est = table.getEstablishment();

		return "redirect:/table/establishment/" + est.getId() ;
	}
	
	@GetMapping("/{id}/seat")
	public String seatTable(Model model, @PathVariable("id") int tableId) {
		RestaurantTable table = this.tableService.findById(tableId);
		table.setHourSeated(Instant.now());
		this.tableService.save(table);
		Establishment est = table.getEstablishment();
		return "redirect:/table/establishment/" + est.getId() ;
	}
	
	@GetMapping("/{id}/unseat")
	public String unseatTable(Model model, @PathVariable("id") int tableId) {
		RestaurantTable table = this.tableService.findById(tableId);
		table.setHourSeated(null);
		table.setOccupied(0);
		this.tableService.save(table);
		Establishment est = table.getEstablishment();
		return "redirect:/table/establishment/" + est.getId() ;
	}
}

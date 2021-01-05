package com.project.TabernasSevilla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;
import com.project.TabernasSevilla.service.EstablishmentService;
import com.project.TabernasSevilla.service.TableService;

@Controller
@RequestMapping("/table")
public class TableController {

	@Autowired
	private TableService tableService;
	@Autowired
	private EstablishmentService establishmentService;

	@GetMapping("establishment/{id}")
	public String manageTables(@PathVariable("id") int establishmentId, Model model) {
		Establishment est = this.establishmentService.findById(establishmentId);
		List<RestaurantTable> tables = this.tableService.findByEstablishment(est);
		model.addAttribute("tables", tables);
		return "tables/list";
	}

	// create table
	@GetMapping("establishment/{id}/add")
	public String addTable(@PathVariable("id") int establishmentId, Model model) {
		Establishment est = this.establishmentService.findById(establishmentId);
		this.tableService.quickCreate(est, 1);

		return "redirect:manager/establishment/" + establishmentId + "/tables";
	}

	// delete table
	@GetMapping("{tableId}/delete")
	public String deleteTable(@PathVariable("tableId") int tableId, Model model) {
		RestaurantTable table = this.tableService.findById(tableId);
		Establishment est = table.getEstablishment();
		this.tableService.delete(table);

		return "redirect:manager/establishment/" + est.getId() + "/tables";
	}

	// modify table
	@GetMapping("{tableId}/modify")
	public String modifyTable(@PathVariable("tableId") int tableId, Model model,@RequestParam(required=true) final Integer cap, @RequestParam(required=false) final Integer oc) {
		RestaurantTable table = this.tableService.findById(tableId);
		table.setSeating(cap);
		if(oc !=null) {
			table.setOccupied(oc);
		}
		this.tableService.save(table);
		Establishment est = table.getEstablishment();

		return "redirect:manager/establishment/" + est.getId() + "/tables";
	}
}
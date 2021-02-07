package com.project.TabernasSevilla.controller;

import java.time.Duration;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.service.EstablishmentService;
import com.project.TabernasSevilla.service.TableService;

@Controller
@RequestMapping("/location")
public class EstablishmentController {

	@Autowired 
	private EstablishmentService establishmentService;
	@Autowired
	private TableService tableService;
	
	@GetMapping("/view")
	public String viewLocation (@RequestParam(required=true) final Integer id, Model model) {
		Establishment est = this.establishmentService.findById(id);
		Assert.notNull(est,"Establishment could not be found");
		Long occupied = this.tableService.getOccupancyAtRestaurant(est); //numero de personas ocupando sitio
		Long capacity = this.tableService.getCapacityAtRestaurant(est);
		String estimate = this.tableService.estimateFreeTable(est) == null ? null : DurationFormatUtils.formatDuration(this.tableService.estimateFreeTable(est), "HH:mm", true);
		model.addAttribute("establishment",est);
		model.addAttribute("occupied",occupied);
		model.addAttribute("capacity",capacity);
		model.addAttribute("estimate",estimate);
		return "establishment/view";
	}
}

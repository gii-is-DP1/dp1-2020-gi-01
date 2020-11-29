package com.project.TabernasSevilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.service.EstablishmentService;

@Controller
@RequestMapping("/location")
public class EstablishmentController {

	@Autowired 
	private EstablishmentService establishmentService;
	
	@GetMapping("/view")
	public String viewLocation (@RequestParam(required=true) final Integer id, Model model) {
		Establishment est = this.establishmentService.findById(id);
		Assert.notNull(est,"Establishment could not be found");
		model.addAttribute("establishment",est);
		return "establishment/view";
	}
}

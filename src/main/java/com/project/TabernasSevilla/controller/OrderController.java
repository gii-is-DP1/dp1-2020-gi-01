package com.project.TabernasSevilla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;
import com.project.TabernasSevilla.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private EstablishmentService estService;
	@Autowired
	private DishService dishService;

	@GetMapping("/est/{id}")
	public String create(Model model,@PathVariable(value="id") Integer establishmentId) {
		RestaurantOrder order = this.orderService.findDraftByPrincipal();
		Establishment est = this.estService.findById(establishmentId);
		if (order == null) {			
			order = this.orderService.initialize(est);
		} else {
			order.setEstablishment(est);
		}
		
		List<Dish> dishes = this.dishService.findAll();
		model.addAttribute("dishes", dishes);
		model.addAttribute("establishment", est);
		model.addAttribute("order", order);
		return "order/edit";
	}
	
	@GetMapping("/")
	public String edit(Model model) {
		RestaurantOrder order = this.orderService.findDraftByPrincipal();
		
		List<Dish> dishes = this.dishService.findAll();
		model.addAttribute("dishes", dishes);
		model.addAttribute("establishment",order.getEstablishment());
		model.addAttribute("order", order);
		return "order/edit";
	}

	@GetMapping("/{orderId}/add/{dishId}")
	public String addDish(Model model, @PathVariable("orderId") int orderId, @PathVariable("dishId") int dishId) {
		try {
			RestaurantOrder order = this.orderService.findById(orderId).get();
			Dish dish = this.dishService.findById(dishId).get();
			this.orderService.addDish(order, dish);
			return "redirect:/order/";
		} catch (Throwable t) {
			List<Dish> dishes = this.dishService.findAll();
			model.addAttribute("dishes", dishes);
			model.addAttribute("message", t.getMessage());
			return "order/edit";
		}
	}

	@GetMapping("/closed")
	public String listInactive(Model model) {
		List<RestaurantOrder> orders = this.orderService.findInactiveByPrincipal();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	@GetMapping("/open")
	public String listActive(Model model) {
		List<RestaurantOrder> orders = this.orderService.findActiveByPrincipal();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	@GetMapping("/{id}")
	public String view(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		model.addAttribute("order", order);
		return "order/view";
	}

	@GetMapping("/{id}/cancel")
	public String cancel(Model model, @PathVariable("id") int orderId) {
		return "redirect: /order/open";
	}

	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		Assert.isTrue(order.getStatus().equals(RestaurantOrder.CLOSED) || order.getStatus().equals(RestaurantOrder.CANCELLED),
				"Cannot delete active order");
		model.addAttribute("order", order);
		return "order/view";
	}

}

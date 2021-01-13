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
import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.OrderLog;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;
import com.project.TabernasSevilla.service.OrderCancellationService;
import com.project.TabernasSevilla.service.OrderLogService;
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
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private OrderCancellationService orderCancellationService;

	@GetMapping("/est/{id}")
	public String create(Model model, @PathVariable(value = "id") Integer establishmentId) {
		RestaurantOrder order = this.orderService.findDraftByPrincipal();
		Establishment est = this.estService.findById(establishmentId);
		if (order == null) {
			order = this.orderService.initialize(est);
		} else {
			order.setEstablishment(est);
		}

		return createRegisterEditModel(order, model);
	}

	@GetMapping("/est/{id}/list")
	public String listActiveByEstablishment(Model model, @PathVariable(value = "id") Integer establishmentId) {
		Establishment est = this.estService.findById(establishmentId);
		List<RestaurantOrder> orders = this.orderService.findActiveByEstablishment(est);
		model.addAttribute("orders", orders);
		model.addAttribute("est", est);
		return "order/list";
	}

	@GetMapping("/est/{id}/list/prev")
	public String listInactiveByEstablishment(Model model, @PathVariable(value = "id") Integer establishmentId) {
		Establishment est = this.estService.findById(establishmentId);
		List<RestaurantOrder> orders = this.orderService.findInactiveByEstablishment(est);
		model.addAttribute("orders", orders);
		model.addAttribute("prev", true);
		model.addAttribute("est", est);
		return "order/list";
	}

	@GetMapping("/")
	public String edit(Model model) {
		try {
			RestaurantOrder order = this.orderService.findDraftByPrincipal();
			return createRegisterEditModel(order, model);
		} catch (Exception e) {
			return "order/view";
		}
	}

	@GetMapping("/{id}/view")
	public String view(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		order.getDish();
		order.getEstablishment();
		if (order.getStatus().equals(RestaurantOrder.DRAFT)) {
			Double total = this.orderService.calculateTotal(order);
			model.addAttribute("total", total);
		} else if (order.getStatus().equals(RestaurantOrder.CANCELLED)) {
			OrderCancellation cancel = this.orderCancellationService.findByOrder(order);
			model.addAttribute("cancel", cancel);
			List<OrderLog> logs = this.orderLogService.findByOrder(order);
			model.addAttribute("logs", logs);
		} else {
			List<OrderLog> logs = this.orderLogService.findByOrder(order);
			model.addAttribute("logs", logs);
		}

		model.addAttribute("order", order);

		return "order/view";
	}

	@GetMapping("/{orderId}/add/{dishId}")
	public String addDish(Model model, @PathVariable("orderId") int orderId, @PathVariable("dishId") int dishId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		try {
			Dish dish = this.dishService.findById(dishId).get();
			this.orderService.addDish(order, dish);
			return "redirect:/order/";
		} catch (Throwable t) {
			return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
					t.getMessage());
		}
	}

	@GetMapping("/{orderId}/remove/{dishId}")
	public String removeDish(Model model, @PathVariable("orderId") int orderId, @PathVariable("dishId") int dishId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		try {
			Dish dish = this.dishService.findById(dishId).get();
			this.orderService.removeDish(order, dish);
			return "redirect:/order/";
		} catch (Throwable t) {
			return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
					t.getMessage());
		}
	}

	@GetMapping("/closed")
	public String listInactive(Model model) {
		List<RestaurantOrder> orders = this.orderService.findInactiveByPrincipal();
		model.addAttribute("orders", orders);
		model.addAttribute("prev", true);
		return "order/list";
	}

	@GetMapping("/open")
	public String listActive(Model model) {
		List<RestaurantOrder> orders = this.orderService.findActiveByPrincipal();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	@GetMapping("/{id}/delete")
	public String delete(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		Assert.isTrue(
				order.getStatus().equals(RestaurantOrder.CLOSED) || order.getStatus().equals(RestaurantOrder.CANCELLED),
				"Cannot delete active order");
		model.addAttribute("order", order);
		return "order/view";
	}

	@GetMapping(value = "/{id}/save")
	public String save(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		Assert.isTrue(this.orderService.checkOwnership(order), "Cannot save this order");
		this.orderService.contextualSave(order);
		return "redirect:/order/open";
	}

	@GetMapping(value = "/checkout")
	public String checkout(@RequestParam(required = true) Integer id, @RequestParam(required = true) String type,
			@RequestParam(required = false) String address, Model model) {
		RestaurantOrder order = this.orderService.findById(id).get();

		order.setType(type);
		order.getEstablishment();
		try {
			if (!address.isBlank()) {
				order.setAddress(address);
			} else {
				Assert.isTrue(type.equals(RestaurantOrder.PICKUP), "Cannot place order without delivery address");
			}
			this.orderService.save(order);
			return "redirect:/order/" + order.getId() + "/view";
		} catch (final Throwable e) {
			return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
					e.getMessage());
		}

	}

	// AUX
	protected String createRegisterEditModel(final RestaurantOrder order, Model model) {
		return this.createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model, null);
	}

	protected String createRegisterEditModel(final RestaurantOrder order, Establishment est, List<Dish> dishes,
			Model model, String message) {
		model.addAttribute(order);
		model.addAttribute("message", message);
		model.addAttribute("dishes", dishes);
		model.addAttribute("establishment", est);
		model.addAttribute("order", order);
		return "order/edit";
	}

}

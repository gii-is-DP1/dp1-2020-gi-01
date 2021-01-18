package com.project.TabernasSevilla.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.OrderCancellationService;
import com.project.TabernasSevilla.service.OrderService;

@Controller
@RequestMapping("/order/cancel")
public class OrderCancellationController {

	@Autowired
	private UserService userService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private OrderCancellationService orderCancellationService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable("id") int orderId) {
		return "order/cancel/view";
	}
	
	@GetMapping("/{id}")
	public String edit(Model model, @PathVariable("id") int orderId) {
		RestaurantOrder order = this.orderService.findById(orderId).get();
		model.addAttribute("order",order);
		OrderCancellation cancel = this.orderCancellationService.initialize(order);
		if(order.getActor().getId()==this.actorService.getPrincipal().getId()) {	
			cancel.setReason("User cancellation");
		}else {
			Assert.isTrue(this.userService.principalHasAnyAuthority(Arrays.asList("ADMIN","MANAGER","COOK","WAITER")),"Cannot cancel order");
			model.addAttribute("cancel",cancel);
		}
		model.addAttribute("cancel",cancel);
		return "order/cancel/edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBooking(@ModelAttribute @Valid final OrderCancellation orderCancellation, final BindingResult binding,
			Model model) {

		if (binding.hasErrors()) {
			model.addAttribute("cancel", orderCancellation);
			return this.createCancelEditModel(orderCancellation, model);
		} else {
			try {
				RestaurantOrder order = orderCancellation.getOrder();
				this.orderCancellationService.save(orderCancellation);
				this.orderService.cancelOrder(order);
				return "redirect:/index";
			} catch (final Exception e) {
				return this.createCancelEditModel(orderCancellation, model, e.getMessage());
			}
		}
	}
	
	private String createCancelEditModel(final OrderCancellation orderCancellation, Model model) {
		return this.createCancelEditModel(orderCancellation, model, null);
	}

	private String createCancelEditModel(OrderCancellation orderCancellation, Model model, String message) {
		
		model.addAttribute("order",orderCancellation.getOrder());
		model.addAttribute("cancel",orderCancellation);
		model.addAttribute("message", message);
		return "order/cancel/edit";
	}
}

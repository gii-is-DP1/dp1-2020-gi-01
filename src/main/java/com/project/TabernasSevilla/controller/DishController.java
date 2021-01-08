package com.project.TabernasSevilla.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.service.DishService;

@Controller
@RequestMapping("/dishes")
public class DishController extends AbstractController {

	@Autowired
	private DishService dishService;

	@GetMapping(path = "/{dishId}")
	public String showDishInfo(@PathVariable("dishId") int dishId, Model model) {
		String view = "dishes/dishInfo"; // vista a la que pasamos la informacion
		Dish dish = dishService.findById(dishId).get(); // plato en concreto, con toda su información
		model.addAttribute("dish", dish);
		return view;
	}

	@GetMapping(path = "/list")
	public String dishList(Model model) {
		List<Dish> dishes = dishService.findAll();
		model.addAttribute("dishes", dishes);
		return "dishes/list";

	}

	@GetMapping(path = "/new")
	public String createDish(Model model) {
		String view = "dishes/createDishForm";
		model.addAttribute("dish", new Dish());
		return view;
	}

	@PostMapping(path = "/save")
	public String saveDish(@Valid Dish dish, BindingResult result, Model model) {
		String view = "redirect:/dishes/list";
		if (result.hasErrors()) {
			model.addAttribute("dish", dish);
			return "dishes/createDishForm";
		} else {
			dishService.save(dish);
			model.addAttribute("message", "Dish successfully saved");
			view = dishList(model);
		}
		return view;
	}

	@GetMapping(path = "/delete/{dishId}")
	public String deleteDish(@PathVariable("dishId") int dishId, Model model) {

		String view = super.checkIfCurrentUserIsAllowed("redirect:/dishes/list", "ADMIN");

		if (view != "error") {
			Optional<Dish> dish = dishService.findById(dishId);
			if (dish.isPresent()) {
				dishService.delete(dish.get());
				model.addAttribute("message", "Dish succesfully deleted");
				view = dishList(model);
			} else {
				model.addAttribute("message", "Dish not found");
				view = dishList(model);
			}
		}

		return view;
	}

	@GetMapping(value = "{dishId}/edit")
	public String updateDishForm(@PathVariable("dishId") int dishId, Model model) {
		String view = "dishes/updateDishForm";
		Optional<Dish> dish = this.dishService.findById(dishId);
		model.addAttribute(dish.get());
		return view;
	}

	@PostMapping(value = "{dishId}/edit")
	public String processUpdateDishForm(@Valid Dish dish, BindingResult result, @PathVariable("dishId") int dishId) {
		String view = "dishes/updateDishForm";

		if (result.hasErrors()) {
			return view;
		} else {

			dish.setId(dishId);

			try {
				this.dishService.save(dish);
			} catch (Exception e) {
				FieldError error = new FieldError("dish", "picture", "Invalid URL");
				result.addError(error);
				return view;
			}

			return "redirect:/dishes/list";
		}
	}

}

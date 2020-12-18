package com.project.TabernasSevilla.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.service.DishService;

@Controller
@RequestMapping("/dishes")
public class DishController {

	@Autowired
	private DishService dishService;

	@GetMapping(path = "/{dishId}")
	public String showDishInfo(@PathVariable("dishId") int dishId, ModelMap modelMap) {
		String view = "dishes/dishInfo"; // vista a la que pasamos la informacion
		Dish dish = dishService.findById(dishId).get(); // plato en concreto, con toda su información
		modelMap.addAttribute("dish", dish);
		return view;
	}

	@GetMapping()
	public String dishList(ModelMap modelMap) {
		String view = "dishes/dishList";
		List<Dish> dishes = dishService.findAll();
		modelMap.addAttribute("dishes", dishes);
		return view;

	}

	@GetMapping(path = "/new")
	public String createDish(ModelMap modelMap) {
		String view = "dishes/createDishForm";
		modelMap.addAttribute("dish", new Dish());
		return view;
	}

	@PostMapping(path = "/save")
	public String saveDish(@Valid Dish dish, BindingResult result, ModelMap modelMap) {
		String view = "dishes/dishList";
		if (result.hasErrors()) {
			modelMap.addAttribute("dish", dish);
			return "dishes/createDishForm";
		} else {
			dishService.save(dish);
			modelMap.addAttribute("message", "Dish successfully saved");
			view = dishList(modelMap);
		}
		return view;
	}

	@GetMapping(path = "/delete/{dishId}")
	public String deleteDish(@PathVariable("dishId") int dishId, ModelMap modelMap) {
		String view = "dishes/dishList";
		Optional<Dish> dish = dishService.findById(dishId);
		if (dish.isPresent()) {
			dishService.delete(dish.get());
			modelMap.addAttribute("message", "Dish succesfully deleted");
			view = dishList(modelMap);
		} else {
			modelMap.addAttribute("message", "Dish not found");
			view = dishList(modelMap);
		}
		return view;
	}

	@GetMapping(value = "{dishId}/edit")
	public String updateDishForm(@PathVariable("dishId") int dishId, ModelMap modelMap) {
		String view = "dishes/updateDishForm";
		Optional<Dish> dish = this.dishService.findById(dishId);
		modelMap.addAttribute(dish.get());
		return view;
	}

	@PostMapping(value = "{dishId}/edit")
	public String processUpdateDishForm(@Valid Dish dish, BindingResult result,
			@PathVariable("dishId") int dishId) {
		String view = "dishes/updateDishForm";
		if (result.hasErrors()) {
			return view;
		} else {
			dish.setId(dishId);
			this.dishService.save(dish);
			return "redirect:/dishes/{dishId}";
		}
	}

}

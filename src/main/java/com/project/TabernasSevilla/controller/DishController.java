package com.project.TabernasSevilla.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.service.DishService;

@Controller

public class DishController {

	@Autowired
	private DishService dishService;

	@RequestMapping("/dishes")
	public String dishList(ModelMap modelMap) {
		String view = "dishList.jsp";
		Iterable<Dish> dishes = dishService.dishList();
		modelMap.addAttribute("dishes", dishes);
		return view;

	}

	@GetMapping(path = "/newDish")
	public String createDish(ModelMap modelMap) {
		String view = "dishes/createOrUpdateDishForm";
		modelMap.addAttribute("dish", new Dish());
		return view;
	}

	@PostMapping(path = "/saveDish") //saveDish en vez de /save porque me decia que contact controller ya lo tenia
	public String saveDish(@Valid Dish dish, BindingResult result, ModelMap modelMap) {
		String view = "dishes/dishList";
		if (result.hasErrors()) {
			modelMap.addAttribute("dish", dish);
			return "dishes/createOrUpdateDishForm";
		} else {
			dishService.saveDish(dish);
			modelMap.addAttribute("message", "Dish successfully saved");
			view = dishList(modelMap);
		}
		return view;
	}

	@GetMapping(path = "/delete/{dishId}")
	public String deleteDish(@PathVariable("dishId") int dishId, ModelMap modelMap) {
		String view = "dishes/dishList";
		Optional<Dish> dish = dishService.dishById(dishId);
		if (dish.isPresent()) {
			dishService.deleteDish(dish.get());
			modelMap.addAttribute("message", "Dish succesfully deleted");
			view = dishList(modelMap);
		} else {
			modelMap.addAttribute("message", "Dish not found");
			view = dishList(modelMap);
		}
		return view;
	}
}

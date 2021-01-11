package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.repository.DishRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class DishServiceTest {
	@Autowired
	public DishService dishService;
	@Autowired
	public DishRepository dishRepo;

	@Test
	void testCount() {
		Integer count = null;
		try {
			count = dishService.count();
		} catch (Exception e) {
			Logger.getLogger(DishServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

		assertThat(count).isNotNull();
	}

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Allergen a1 = new Allergen("GLUTEN", "GLU",
				"https://cdn.icon-icons.com/icons2/852/PNG/512/IconoAlergenoGluten-Gluten_icon-icons.com_67600.png");
		Allergen a2 = new Allergen("LACTOSA", "LACTO", "https://www.flaticon.es/svg/static/icons/svg/1624/1624652.svg");
		List<Allergen> allergensFromD = new ArrayList<>();
		allergensFromD.add(a1);
		allergensFromD.add(a2);
		Dish dish = new Dish(); // job application
		dish.setDescription("Que rico");
		dish.setPicture(" ");
		dish.setScore(2.0);
		dish.setAllergens(allergensFromD);
		dish.setName("Burger");
		dish.setPrice(5.0);
		try {
			this.dishService.save(dish);
		} catch (Exception e) {
			Logger.getLogger(DishServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(dish.getId()).isNotNull();
	}

	@Test
	void testFindById() {
		// Creo el objeto

		Allergen a1 = new Allergen("GLUTEN", "GLU",
				"https://cdn.icon-icons.com/icons2/852/PNG/512/IconoAlergenoGluten-Gluten_icon-icons.com_67600.png");
		Allergen a2 = new Allergen("LACTOSA", "LACTO", "https://www.flaticon.es/svg/static/icons/svg/1624/1624652.svg");

		List<Allergen> allergensFromD = new ArrayList<>();
		allergensFromD.add(a1);
		allergensFromD.add(a2);

		// Guardo el objeto

		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 5.0,
				allergensFromD);
		
		Dish d1 = new Dish();
		d1 = this.dishRepo.save(d);
		try { 
			d1 = this.dishService.findById(d.getId()).get();	
		} catch (Exception e) {
			Logger.getLogger(DishServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(d1.getName().equals("Mi plato"));
		assertThat(d1.getPrice().equals(20.0));

	}

	@Test
	void testFindAll() {
		List<Dish> a = new ArrayList<>();
		Dish dish = new Dish();
		this.dishRepo.save(dish);
		try {
			a = this.dishService.findAll();
		} catch (Exception e) {
			Logger.getLogger(DishServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(a).isNotEmpty();
	}

}

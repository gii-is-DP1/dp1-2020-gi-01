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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.repository.AllergenRepository;
import com.project.TabernasSevilla.repository.DishRepository;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class DishServiceTest {
	@Autowired
	public DishService dishService;
	@Autowired
	public DishRepository dishRepo;
	
	@Autowired
	public AllergenRepository repositoryAllergen;

	@WithMockUser(value = "spring")
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
	public void testSaveandFindyById() {
		Allergen a1 = new Allergen("GLUTEN", "GLU",
				"https://cdn.icon-icons.com/icons2/852/PNG/512/IconoAlergenoGluten-Gluten_icon-icons.com_67600.png");
		Allergen a2 = new Allergen("LACTOSA", "LACTO", "https://www.flaticon.es/svg/static/icons/svg/1624/1624652.svg");
		
		Allergen savedA1 = this.repositoryAllergen.save(a1);
		Allergen savedA2 = this.repositoryAllergen.save(a2);
		
		List<Allergen> allergensFromD = new ArrayList<>();
		allergensFromD.add(savedA1);
		allergensFromD.add(savedA2);
		
		Dish dish = new Dish(); 
		dish.setDescription("Que rico");
		dish.setPicture("https://www.casaviva.es/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/2/0/2011420.jpg");
		dish.setScore(2.0);
		dish.setAllergens(allergensFromD);
		dish.setName("Burger");
		dish.setPrice(5.0);
		dish.setIsVisible(true);
		Dish d1 = this.dishService.save(dish);
		d1 = this.dishService.findById(dish.getId()).get();
		System.out.println("Hola" + d1.getId());
		assertThat(d1).isNotNull();
	}

}

package com.project.TabernasSevilla.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.repository.AllergenRepository;
import com.project.TabernasSevilla.repository.DishRepository;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserRepository;

@Configuration
public class PopulatorDatabase implements CommandLineRunner {

	@Autowired
	private DishRepository repository;

	@Autowired
	private AllergenRepository repositoryAllergen;

	@Autowired
	private AuthorityRepository repositoryAuthority;

	@Autowired
	private UserRepository repositoryUser;

	@Override
	public void run(String... args) throws Exception {

		// Creo el objeto

		Allergen a1 = new Allergen("GLUTEN", "GLU",
				"https://cdn.icon-icons.com/icons2/852/PNG/512/IconoAlergenoGluten-Gluten_icon-icons.com_67600.png");
		Allergen a2 = new Allergen("LACTOSA", "LACTO", "https://www.flaticon.es/svg/static/icons/svg/1624/1624652.svg");

		// Guardo el objeto en BBDD

		Allergen savedA1 = this.repositoryAllergen.save(a1);
		Allergen savedA2 = this.repositoryAllergen.save(a2);

		List<Allergen> allergensFromD = new ArrayList<>();
		allergensFromD.add(savedA1);
		allergensFromD.add(savedA2);

		// Guardo el objeto

		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 5.0,
				allergensFromD);

		Dish saved = this.repository.save(d);

	}

}

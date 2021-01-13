package com.project.TabernasSevilla.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Review;
import com.project.TabernasSevilla.repository.AbstractActorRepository;
import com.project.TabernasSevilla.repository.AdminRepository;
import com.project.TabernasSevilla.repository.AllergenRepository;
import com.project.TabernasSevilla.repository.DishRepository;
import com.project.TabernasSevilla.repository.ReviewRepository;
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

	//@Autowired
	//private UserRepository repositoryUser;
	
	@Autowired 
	private ReviewRepository repoReview;
	
	@Autowired 
	private AdminRepository repoAdmin;
	
	//@Autowired 
	//private AbstractActorRepository repoActor;

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
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0,
				allergensFromD);
		//creo un user para la review
		//crear user y de ahi actor con sus propiedades de constructor
		
		/*
		User us = new User();
		us.setUsername("Anthony Fantano");
		us.setPassword("fantano");
		Authority auth = new Authority();
		auth.setAuthority("reviewer");
		Set<Authority> ls = new HashSet<>();
		ls.add(auth);
		us.setAuthorities(ls);
		*/
		
		//Actor persona = new Actor();
		
		
		//Actor ActSave =  this.repoActor.save(persona);
		
		//creo review
		//Review rev = new Review(persona, d, "ta rico la verdad", 4);
		//Review Rsaved = this.repoReview.save(rev);
		
		
		
		Dish saved = this.repository.save(d);
		
		// la review bien, hay que guardar dish antes de meterlo en review
		Admin ad = repoAdmin.getOne(1);
		Review rev = new Review(ad, d, "ta rico la verdad", 4.0); //actor, dish, comentario, puntuacion
		Review Rsaved = this.repoReview.save(rev);
		
		

	}
	
	//INSERT INTO REVIEW(id, version, comment, rating, actor_id, dish_id) VALUES (1, 0, 'ta rico la verdad', 4, 1, 3);
	
	
	

}

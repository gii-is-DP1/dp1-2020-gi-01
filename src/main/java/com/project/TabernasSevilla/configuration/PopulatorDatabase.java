package com.project.TabernasSevilla.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Review;
import com.project.TabernasSevilla.domain.Seccion;
import com.project.TabernasSevilla.repository.AdminRepository;
import com.project.TabernasSevilla.repository.AllergenRepository;
import com.project.TabernasSevilla.repository.DishRepository;
import com.project.TabernasSevilla.repository.ReviewRepository;

@Configuration
public class PopulatorDatabase implements CommandLineRunner {

	@Autowired
	private DishRepository repository;

	@Autowired
	private AllergenRepository repositoryAllergen;

	// @Autowired
	// private UserRepository repositoryUser;

	@Autowired
	private ReviewRepository repoReview;

	@Autowired
	private AdminRepository repoAdmin;

	// @Autowired
	// private AbstractActorRepository repoActor;

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

		Dish dEntrantes1 = new Dish("Gulas al Ajillo", "Exquisita tosta crujiente con gulas de Cádiz",
				"http://localhost:8080/images/gulas.jpg", 5.0, 3.0, Seccion.ENTRANTES, true, allergensFromD);

		Dish dBebidas1 = new Dish("Coca-Cola", "Refrescante bebida de cola", "http://localhost:8080/images/coke.jpg",
				1.5, 0.0, Seccion.BEBIDAS, true, null);

		Dish dVinos1 = new Dish("Vino Tinto", "Cosecha del 92", "http://localhost:8080/images/vinotinto.jpg", 10.0, 0.0,
				Seccion.VINOS, true, null);

		Dish dEnsaladas1 = new Dish("Ensalada César", "Lechugas de origen español",
				"http://localhost:8080/images/ensaladacesar.jpg", 5.0, 0.0, Seccion.ENSALADAS, true, allergensFromD);

		Dish dPescados1 = new Dish("Puntillitas", "El placer del mar en tu plato",
				"http://localhost:8080/images/puntillitas.jpg", 2.5, 0.0, Seccion.PESCADOS, true, null);

		Dish dCarnes1 = new Dish("Solomillo de Cerdo", "Carne al punto en su salsa",
				"http://localhost:8080/images/solomillo.jpg", 5.0, 0.0, Seccion.CARNES, true, null);

		Dish dPostres1 = new Dish("Tocino de cielo", "Dulce postre casero", "http://localhost:8080/images/tocino.jpg",
				2.4, 0.0, Seccion.POSTRES, true, allergensFromD);

		// creo un user para la review
		// crear user y de ahi actor con sus propiedades de constructor

		/*
		 * User us = new User(); us.setUsername("Anthony Fantano");
		 * us.setPassword("fantano"); Authority auth = new Authority();
		 * auth.setAuthority("reviewer"); Set<Authority> ls = new HashSet<>();
		 * ls.add(auth); us.setAuthorities(ls);
		 */

		// Actor persona = new Actor();

		// Actor ActSave = this.repoActor.save(persona);

		// creo review
		// Review rev = new Review(persona, d, "ta rico la verdad", 4);
		// Review Rsaved = this.repoReview.save(rev);

		Dish savedEN1 = this.repository.save(dEntrantes1);
		Dish savedB1 = this.repository.save(dBebidas1);
		Dish savedV1 = this.repository.save(dVinos1);
		Dish savedE1 = this.repository.save(dEnsaladas1);
		Dish savedPE1 = this.repository.save(dPescados1);
		Dish savedC1 = this.repository.save(dCarnes1);
		Dish savedP1 = this.repository.save(dPostres1);

		// la review bien, hay que guardar dish antes de meterlo en review
		Admin ad = repoAdmin.getOne(1);
		Review rev = new Review(ad, dEntrantes1, "Example review", 3.0); // actor, dish, comentario, puntuacion
		Review Rsaved = this.repoReview.save(rev);

	}

	// INSERT INTO REVIEW(id, version, comment, rating, actor_id, dish_id) VALUES
	// (1, 0, 'ta rico la verdad', 4, 1, 3);

}

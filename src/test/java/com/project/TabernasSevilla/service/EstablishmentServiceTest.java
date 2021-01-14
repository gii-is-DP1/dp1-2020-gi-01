package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class EstablishmentServiceTest{
	@Autowired
	public EstablishmentService establishmentService;
	
	@Test
	public void shouldCreateAnInstanceCorrectly() {
		List<Dish> dish = new ArrayList<>();
		Establishment est = new Establishment(); //job application
		est.setAddress("Calle Flor de Pascua 6");
		est.setCapacity(100);
		est.setCurrentCapacity(50);
		est.setDish(dish);
		est.setPhone("655344355");
		est.setTitle("Arenal");
		est.setOpeningHours("8:00 - 22:00");
		est.setDescription("Ven a degustar nuestro magnífico menú");
		est.setPicture("avatar");
		
		try {
			this.establishmentService.save(est);
		} catch (Exception e) {
			Logger.getLogger(EstablishmentServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		//System.out.println("############ todos los establecimientos uwu: "+ establishmentService.findAll()); da error el findAll
		assertThat(est.getId()).isNotNull();
	}

}

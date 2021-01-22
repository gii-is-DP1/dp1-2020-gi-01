package com.project.TabernasSevilla.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.CookRepository;
import com.project.TabernasSevilla.repository.EstablishmentRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class EstablishmentServiceTest{
	@Autowired
	public EstablishmentService establishmentService;
	
	@Autowired
	public EstablishmentRepository repositoryEstablishment;
	
	@Autowired
	public CookRepository repositoryCook;
	
	
	@Test
	public void testAddAllCooks(){
		Establishment est = new Establishment();
		est.setAddress("Calle Amor 4");
		est.setCapacity(100);
		est.setTitle("Feria");
		est.setOpeningHours("8:00 - 18:00");
		est.setCurrentCapacity(2);
		est.setScore(2);
		Establishment saved = this.repositoryEstablishment.save(est);
		
//		Allergen savedA2 = this.repositoryAllergen.save(a2);
		Cook a = new Cook();
		a.setName("Pepe");
//		TENGO QUE SETEAR LOS 2 COOKS Y VER QUE PASA CON EL SQL, NO ME PREOCUPA PORQUE SI BORRO ESE ESTABLISHMENT SIGUE SALIENDO BIEN
		Cook b = new Cook();
		Cook savedC1 = this.repositoryCook.save(a);
		Cook savedC2 = this.repositoryCook.save(b);
		Integer[] cookId = {0,0};
		cookId[0]=savedC1.getId();
		cookId[1]=savedC2.getId();
		Establishment added = this.establishmentService.addAllCooks(saved.getId(), cookId);
		assertThat(added).isNotNull();
	}
	
//	public Establishment addAllCooks(Integer estId, Integer[] cookId) {
//		Establishment est = this.findById(estId);
//		List<Cook> cooks = new ArrayList<>();
//		for (int i = 0; i < cookId.length; i++) {
//			Cook c = this.cookService.findById(cookId[i]);
//			cooks.add(c);
//		}
//		est.setCook(cooks);
//		return this.save(est);
//	}
	
	@Test
	public void testAddAllManager(){
		
	}
	
	@Test
	public void testAddAllWaiter(){
		
	}

}

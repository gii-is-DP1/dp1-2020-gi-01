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
	public void testAddAllManager(){
		
	}
	
	@Test
	public void testAddAllWaiter(){
		
	}

}

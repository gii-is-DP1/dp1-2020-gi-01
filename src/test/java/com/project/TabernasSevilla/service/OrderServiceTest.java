package com.project.TabernasSevilla.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class OrderServiceTest {
	@Autowired
	protected OrderService orderService;

	
	@Test
	public void testAddDish(){
		
	}
	@Test
	public void testRemoveDish(){
		
	}
	@Test
	public void testcheckOwnership(){
		
	}
	@Test
	public void testCalculateTotal(){
		
	}
	@Test
	public void testUpdateStatus(){
		
	}
}

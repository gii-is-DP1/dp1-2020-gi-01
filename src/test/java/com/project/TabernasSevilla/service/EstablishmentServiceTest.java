package com.project.TabernasSevilla.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Manager;
import com.project.TabernasSevilla.domain.Waiter;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class EstablishmentServiceTest{
	@Autowired
	public EstablishmentService establishmentService;
	
	@Autowired
	public CookService cookService;
	
	@Autowired
	public ManagerService managerService;
	
	@Autowired
	public WaiterService waiterService;
	
	@Test
	public void testAddAllCooks(){
		Cook a = new Cook();
		a.setName("Adrian");
		a.setSurname("Pérez");
		Cook saved1 = this.cookService.save(a);
		Cook b = new Cook();
		b.setName("Adriana");
		b.setSurname("López");
		Cook saved2= this.cookService.save(b);
		Integer[] cookId = new Integer[2];
		cookId[0] = saved1.getId();
		cookId[1] = saved2.getId();
		Establishment est = new Establishment();
		est = this.establishmentService.addAllCooks(2,cookId);
		assert(est.getCook().contains(this.cookService.findById(saved1.getId())));
	}
	
	@Test
	public void testAddAllManager(){
		Manager a = new Manager();
		a.setName("Adrian");
		a.setSurname("Pérez");
		Manager saved1 = this.managerService.save(a);
		Manager b = new Manager();
		b.setName("Adriana");
		b.setSurname("López");
		Manager saved2= this.managerService.save(b);
		Integer[] managerId = new Integer[2];
		managerId[0] = saved1.getId();
		managerId[1] = saved2.getId();
		Establishment est = new Establishment();
		est = this.establishmentService.addAllManagers(2, managerId);
		assert(est.getManager().contains(this.managerService.findById(saved1.getId())));
	}
	
	@Test
	public void testAddAllWaiter(){
		Waiter a = new Waiter();
		a.setName("Adrian");
		a.setSurname("Pérez");
		Waiter saved1 = this.waiterService.save(a);
		Waiter b = new Waiter();
		b.setName("Adriana");
		b.setSurname("López");
		Waiter saved2 = this.waiterService.save(b);
		Integer[] waiterId = new Integer[2];
		waiterId[0] = saved1.getId();
		waiterId[1] = saved2.getId();
		Establishment est = new Establishment();
		est = this.establishmentService.addAllWaiters(2,waiterId);
		assert(est.getWaiter().contains(this.waiterService.findById(saved1.getId())));
	}

}

package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.RestaurantOrder;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class OrderServiceTest {
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected DishService dishService;
	
	@Autowired
	protected AdminService adminService;
	
	@Autowired
	protected EstablishmentService estService;

	
	@Test
	public void testAddDish(){
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		Dish dish = new Dish(); 
		dish.setDescription("Que rico");
		dish.setPicture("https://www.casaviva.es/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/2/0/2011420.jpg");
		dish.setScore(2.0);
		dish.setName("Burger");
		dish.setPrice(5.0);
		dish.setIsVisible(true);
		Dish d1 = this.dishService.save(dish);
		order =  this.orderService.addDish(order, d1);
		assertThat(order.getDish().get(0)).isEqualTo(d1);
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
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		RestaurantOrder updated = this.orderService.updateStatus(order, RestaurantOrder.DELIVERED);
		assertThat(updated).isNull();
	}
}

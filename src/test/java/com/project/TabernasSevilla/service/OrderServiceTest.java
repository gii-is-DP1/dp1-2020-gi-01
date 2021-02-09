package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

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
	
	@MockBean
	protected UserService userService;
	
	@MockBean
	protected ActorService actorService;

	
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
	public void contextualSaveTest() {
		
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);
		given(this.actorService.getPrincipal()).willReturn(this.adminService.findAll().get(0));
		
		RestaurantOrder order = this.orderService.initialize(this.estService.findById(1));
		order.setAddress("en fin");
		this.orderService.contextualSave(order);
		assertThat(order.getActor()).isEqualTo(this.adminService.findAll().get(0));
		
	}
	
	@Test
	public void cancellOrder() {
		RestaurantOrder order = this.orderService.initialize(this.estService.findById(1));
		order = this.orderService.cancelOrder(order);
		assertThat(order.getStatus()).isEqualTo(RestaurantOrder.CANCELLED);
	}
	
	@Test
	public void testRemoveDish(){
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
		order =  this.orderService.removeDish(order, d1);
		assertThat(order.getDish()).isEmpty();
	}
	@Test
	public void testcheckOwnership(){
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		Boolean res = this.orderService.checkOwnership(order, this.adminService.findAll().get(0).getId());
		assert(res).equals(true);
	}
	@Test
	public void testCalculateTotal(){
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
		Dish d2 = new Dish(); 
		d2.setDescription("Que rica");
		d2.setPicture("https://www.casaviva.es/media/catalog/product/cache/1/thumbnail/9df78eab33525d08d6e5fb8d27136e95/2/0/2011420.jpg");
		d2.setScore(3.0);
		d2.setName("Cheeseburger");
		d2.setPrice(10.0);
		d2.setIsVisible(true);
		this.dishService.save(d2);
		this.orderService.addDish(order, d1);
		this.orderService.addDish(order, d2);
		Double res = this.orderService.calculateTotal(order);
		assertThat(res).isEqualTo(d1.getPrice()+d2.getPrice());
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
		//Pongo true para hacer efectivo que tenga autoridad
		given(this.userService.principalIsEmployee()).willReturn(true);
		RestaurantOrder updated = this.orderService.updateStatus(order, RestaurantOrder.DELIVERED, this.userService.principalIsEmployee());
		assertThat(updated).isNotNull();
	}	
	
	@Test
	public void shouldFindActiveByEstablishment() {
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		List<RestaurantOrder> list = this.orderService.findActiveByEstablishment(this.estService.findById(1));
		assertThat(list.get(0)).isEqualTo(order);
	}
	
	@Test
	public void shouldFindInactiveByEstablishment() {
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.CLOSED);
		this.orderService.save(order);
		List<RestaurantOrder> list = this.orderService.findInactiveByEstablishment(this.estService.findById(1));
		assertThat(list.get(0)).isEqualTo(order);
	}
	
	@Test
	public void shouldFindInactiveByPrincipal() {
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.CLOSED);
		this.orderService.save(order);
		List<RestaurantOrder> list = this.orderService.findInactiveByPrincipal(this.adminService.findAll().get(0));
		assertThat(list.get(0)).isEqualTo(order);
	}
	
	@Test
	public void shouldFindActiveByPrincipal() {
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		List<RestaurantOrder> list = this.orderService.findActiveByPrincipal(this.adminService.findAll().get(0));
		assertThat(list.get(0)).isEqualTo(order);
	}
	
}

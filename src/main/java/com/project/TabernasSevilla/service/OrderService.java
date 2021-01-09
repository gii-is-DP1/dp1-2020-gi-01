package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.repository.OrderRepository;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private UserService userService;
	
	// CRUD METHODS FOR ORDER
	public Optional<RestaurantOrder> findById(int id) {
		return this.orderRepo.findById(id);
	}
	
	public List<RestaurantOrder> findAll(){
		return this.orderRepo.findAll();
	}
	
	public RestaurantOrder save(RestaurantOrder order) {
		
		return this.orderRepo.save(order);
	}
	
	public RestaurantOrder create() {
		RestaurantOrder res = new RestaurantOrder();
		return res;
	}
	
	public void delete(RestaurantOrder order) {
		Assert.isTrue(order.getActor().equals(this.actorService.getPrincipal()),"Order could not be deleted");
		this.orderRepo.delete(order);
	}
	
	//OTHER METHODS
	
//	//Create order for booking and set type
//	public Order createFromBooking(Booking booking) {
//		Order order = this.create();
//		order.setBooking(booking);
//		order.setType(Order.EAT_IN);
//		return order;
//	}
//	
	
	public RestaurantOrder findDraftByActor(Actor actor){
		return this.orderRepo.findDraftByActor(actor.getId());
	}
	
	public RestaurantOrder addDish(RestaurantOrder order, Dish dish) {
		Assert.isTrue(order.getStatus().equals(RestaurantOrder.DRAFT) || order.getStatus().equals(RestaurantOrder.OPEN),"Cannot add dish to order");
		List<Dish> dishes = order.getDish();
		dishes.add(dish);
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	public RestaurantOrder findDraftByPrincipal(){
		Actor actor = this.actorService.getPrincipal();
		return this.findDraftByActor(actor);
	}
	
	public List<RestaurantOrder> findActiveByPrincipal(){
		Actor actor = this.actorService.getPrincipal();
		return this.orderRepo.findActiveByActor(actor.getId());
	}
	
	public List<RestaurantOrder> findInactiveByPrincipal(){
		Actor actor = this.actorService.getPrincipal();
		return this.orderRepo.findInactiveByActor(actor.getId());
	}
	
	public RestaurantOrder initialize(Establishment est) {
		RestaurantOrder order = this.create();
		Actor actor = this.actorService.getPrincipal();
		order.setActor(actor);
		order.setDish(new ArrayList<Dish>());
		order.setEstablishment(est);
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.DRAFT);
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	//online type order
	public RestaurantOrder placeOrder(RestaurantOrder order) {
		Assert.isTrue(!order.getType().equals("EAT-IN"),"Cannot place this type of order");
		Assert.isTrue(order.getActor().equals(this.actorService.getPrincipal()),"Cannot place order for someone else");
		order.setStatus(RestaurantOrder.PLACED);
		//log order placed
		this.orderLogService.log(order, order.getStatus());
		order.setPlacementDate(Instant.now());
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	//open orders can be modified
	public RestaurantOrder openOrder(RestaurantOrder order) {
		Assert.isTrue(order.getType().equals("EAT-IN"),"Cannot keep this order open");		
		Assert.isTrue(this.userService.principalHasAnyAuthority(Arrays.asList("ADMIN","MANAGER","COOK","WAITER")),"Cannot place order for someone else");
		order.setStatus(RestaurantOrder.OPEN);
		//log open order
		this.orderLogService.log(order, order.getStatus());
		order.setPlacementDate(Instant.now());
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	public RestaurantOrder cancelOrder(RestaurantOrder order) {
		if(this.userService.principalHasAuthority("CUSTOMER")) {
			Assert.isTrue(order.getActor().equals(this.actorService.getPrincipal()),"Cannot cancel order for someone else");
		}else {
			Assert.isTrue(this.userService.principalHasAnyAuthority(Arrays.asList("ADMIN","MANAGER","COOK","WAITER")),"Cannot cancel order");
		}
		order.setStatus(RestaurantOrder.CANCELLED);
		//log order cancellation
		this.orderLogService.log(order, order.getStatus());
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	

}

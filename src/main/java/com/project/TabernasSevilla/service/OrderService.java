package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.repository.OrderRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class OrderService {

 
	private OrderRepository orderRepo;
 
	private OrderLogService orderLogService;
 
	private ActorService actorService;
 
	private UserService userService;
	
	@Autowired
	public OrderService(OrderRepository orderRepo, OrderLogService orderLogService, ActorService actorService,
			UserService userService) {
		super();
		this.orderRepo = orderRepo;
		this.orderLogService = orderLogService;
		this.actorService = actorService;
		this.userService = userService;
	}

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
	
	public RestaurantOrder removeDish(RestaurantOrder order, Dish dish) {
		Assert.isTrue(order.getStatus().equals(RestaurantOrder.DRAFT) || order.getStatus().equals(RestaurantOrder.OPEN),"Cannot remove dish from order");
		List<Dish> dishes = order.getDish();
		dishes.remove(dish);
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	public RestaurantOrder findDraftByPrincipal(){
		Actor actor = this.actorService.getPrincipal();
		return this.findDraftByActor(actor);
	}
	
	public List<RestaurantOrder> findActiveByEstablishment(Establishment est){
		return this.orderRepo.findActiveByEstablishment(est.getId());
	}
	
	public List<RestaurantOrder> findInactiveByEstablishment(Establishment est){
		return this.orderRepo.findInactiveByEstablishment(est.getId());
	}
	
	
	public List<RestaurantOrder> findActiveByPrincipal(Actor actor){
		return this.orderRepo.findActiveByActor(actor.getId());
	}
	
	public List<RestaurantOrder> findInactiveByPrincipal(Actor actor){
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
	
	public Boolean checkOwnership(RestaurantOrder order, int id) {
		return order.getActor().getId() == id;
	}
	
	public RestaurantOrder contextualSave(RestaurantOrder order) {
		
		//no puedes pedir más de 20 items en un pedido
		Integer numeroDishes = order.getDish().size();
		System.out.println("=============== numero paltos: " + numeroDishes);
		Assert.isTrue(!(numeroDishes>20), "You can't order more than 20 items! Items ordered: " +numeroDishes );
		
		
		//no puedes tener mas de 2 pedidos en activo
		Integer n2 = 0;
		for(RestaurantOrder o: orderRepo.findActiveByActor(order.getActor().getId()) ) {
			n2++;
		}
		Assert.isTrue(!(n2>1), "You can't have more than 2 active orders. Please wait until your order's states are closed"); //hay que poner uno menos del que realmente queremos ok?
		
		switch(order.getType()) {
		case RestaurantOrder.EAT_IN:
			Assert.isTrue(this.userService.principalHasAnyAuthority(Arrays.asList("WAITER","COOK","MANAGER","ADMIN")),"Cannot save type of order: not an employee");
			return this.openOrder(order);
			
			
		case RestaurantOrder.DELIVERY:
			
			//no puedes dejar la direccion vacía si pides para delivery
			Assert.isTrue(!order.getAddress().isBlank() && order.getAddress()!=null,"Order needs an address for delivery");
			

		default:
			return this.placeOrder(order);
		}
	}
	
	public Double calculateTotal(RestaurantOrder order) {
		Double res = 0d;
		for(Dish d: order.getDish()) {
			res = res + d.getPrice();
		}
		return res;
	}
	
	//online type order
	public RestaurantOrder placeOrder(RestaurantOrder order) {
		order.getActor();
		Assert.isTrue(!order.getType().equals("EAT-IN"),"Cannot place this type of order");
		Assert.isTrue(order.getActor().getId()==this.actorService.getPrincipal().getId(),"Cannot place order for someone else");
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
		order.setStatus(RestaurantOrder.CANCELLED);
		//log order cancellation
		this.orderLogService.log(order, order.getStatus());
		RestaurantOrder saved = this.save(order);
		return saved;
	}
	
	//TODO: better way to handle this
	public RestaurantOrder updateStatus(RestaurantOrder order, String status, Boolean isEmployee) {
		Assert.isTrue(isEmployee,"Unsuficiant authority");
		order.setStatus(status);
		RestaurantOrder saved = this.save(order);
		this.orderLogService.log(saved, saved.getStatus());
		return saved;
	}
	

}

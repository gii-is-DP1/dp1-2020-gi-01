package com.project.TabernasSevilla.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Order;
import com.project.TabernasSevilla.domain.Table;
import com.project.TabernasSevilla.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	// CRUD METHODS
	public Optional<Order> findById(int id) {
		return this.orderRepo.findById(id);
	}
	
	public List<Order> findAll(){
		return this.orderRepo.findAll();
	}
	
	public Order save(Order order) {
		return this.orderRepo.save(order);
	}
	
	public Order create() {
		Order res = new Order();
		return res;
	}
	
	public void delete(Order order) {
		this.orderRepo.delete(order);
	}
	
	//OTHER METHODS
	
	//Create order for booking and set type
	public Order createFromBooking(Booking booking) {
		Order order = this.create();
		order.setBooking(booking);
		order.setType(Order.EAT_IN);
		return order;
	}
	
	//Create order for table and set type
	public Order createFromTable(Table table) {
		Order order = this.create();
		order.setTable(table);
		order.setType(Order.EAT_IN);
		return order;
	}
	
	//Set order to active, add placement date and create first log for order
	public Order initializeAndSave(Order order) {
		order.setActive(true);
		order.setPlacementDate(LocalDateTime.now());
		Order saved = this.save(order);
		//TODO: create orderlog with open status
		return saved;
	}
	
	//Set active to false
	public Order setInactive(Order order) {
		order.setActive(false);
		return this.save(order);
	}

}

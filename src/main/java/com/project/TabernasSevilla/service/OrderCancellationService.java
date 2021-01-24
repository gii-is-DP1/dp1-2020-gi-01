package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.repository.OrderCancellationRepository;

@Service
@Transactional
public class OrderCancellationService {

 
	private OrderCancellationRepository orderCancellationRepo;
	
	@Autowired
	public OrderCancellationService(OrderCancellationRepository orderCancellationRepo) {
		super();
		this.orderCancellationRepo = orderCancellationRepo;
	}

	public Optional<OrderCancellation> findById(int id) {
		return this.orderCancellationRepo.findById(id);
	}
	
	public OrderCancellation create() {
		return new OrderCancellation();
	}
	
	public OrderCancellation initialize(RestaurantOrder order) {
		OrderCancellation res = this.create();
		res.setPlacementDate(Instant.now());
		res.setOrder(order);
		return res;
	}
	
	public OrderCancellation findByOrder(RestaurantOrder order) {
		return this.orderCancellationRepo.findByOrder(order.getId());
	}
	
	public OrderCancellation save(OrderCancellation orderCancellation) {
		orderCancellation.setPlacementDate(Instant.now());
		return this.orderCancellationRepo.save(orderCancellation);
	}
}

package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.OrderLog;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.repository.OrderLogRepository;

@Service
@Transactional
public class OrderLogService {

	@Autowired
	private OrderLogRepository orderLogRepo;
	
	
	public List<OrderLog> findByOrder (RestaurantOrder order){
		return this.orderLogRepo.findByOrder(order.getId());
	}
	
	public OrderLog create() {
		OrderLog res = new OrderLog();
		return res;
	}
	
	public OrderLog log(RestaurantOrder order,String status) {
		OrderLog log = this.create();
		log.setMoment(Instant.now());
		log.setOrder(order);
		log.setStatus(status);
		return this.save(log);
	}

	
	public OrderLog save(OrderLog log) {
		return this.orderLogRepo.save(log);
	}
	
	public void delete(OrderLog log) {
		this.orderLogRepo.delete(log);
	}
}

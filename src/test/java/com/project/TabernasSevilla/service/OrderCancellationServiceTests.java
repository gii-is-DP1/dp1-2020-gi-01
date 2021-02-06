package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.RestaurantOrder;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class OrderCancellationServiceTests {
	
	@Autowired
	protected OrderCancellationService oCService;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected EstablishmentService estService;
	
	@Autowired
	protected AdminService adminService;
	
	@Test
	public void shouldFindByOrder() {
		OrderCancellation o = new OrderCancellation();
		o.setReason("No tengo hambre");
		o.setPlacementDate(Instant.now());
		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		this.orderService.save(order);
		o.setOrder(order);
		this.oCService.save(o);
		OrderCancellation found = this.oCService.findByOrder(order);
		assertThat(found).isEqualTo(o);
	}
	
}

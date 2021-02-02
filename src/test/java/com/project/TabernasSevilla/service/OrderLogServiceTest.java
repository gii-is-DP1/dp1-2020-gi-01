package com.project.TabernasSevilla.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.OrderLog;
import com.project.TabernasSevilla.domain.RestaurantOrder;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class OrderLogServiceTest {

	@Autowired
	protected OrderLogService oLService;

	@Autowired
	protected OrderService oService;

	@Autowired
	protected AdminService adminService;

	@Autowired
	protected EstablishmentService estService;

	@Test
	public void shouldFindByOrder() {

		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.estService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(this.adminService.findAll().get(0));
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.CANCELLED);
		this.oService.save(order);
		OrderLog o = new OrderLog();
		o.setStatus("En camino");
		o.setMoment(Instant.now());
		o.setOrder(order);
		this.oLService.save(o);
		List<OrderLog> found = this.oLService.findByOrder(order);
		assertThat(found.get(0)).isEqualTo(o);
	}

}

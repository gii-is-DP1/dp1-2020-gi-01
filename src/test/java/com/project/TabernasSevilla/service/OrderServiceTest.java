package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.domain.RestaurantTable;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class OrderServiceTest {
	@Autowired
	protected OrderService orderService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		RestaurantOrder order = new RestaurantOrder(); // job application
		order.setActor(new Customer());
		order.setAddress("Calle Flor de Albahaca 7");
		order.setDish(new ArrayList<Dish>());
		order.setEstablishment(new Establishment());
		order.setPlacementDate(Instant.now());
		order.setStatus("Preparing");
		order.setTable(new RestaurantTable());
		try {
			this.orderService.save(order);
		} catch (Exception e) {
			Logger.getLogger(OrderServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(order.getId()).isNotNull();
	}
}

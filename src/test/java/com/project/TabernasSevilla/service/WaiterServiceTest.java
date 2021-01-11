package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Waiter;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class WaiterServiceTest {
	@Autowired
	protected WaiterService waiterService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Waiter waiter = new Waiter(); // job application
		waiter.setAvatar("foto");
		waiter.setEmail("paco@us.es");
		waiter.setName("Paco");
		waiter.setPhoneNumber("692398182");
		waiter.setSurname("Zamudio");
		try {
			this.waiterService.save(waiter);
		} catch (Exception e) {
			Logger.getLogger(WaiterServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(waiter.getId()).isNotNull();
	}

}

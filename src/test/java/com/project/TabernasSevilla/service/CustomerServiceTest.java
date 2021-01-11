package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.project.TabernasSevilla.domain.Customer;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CustomerServiceTest {
	@Autowired
	protected CustomerService customerService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Customer customer = new Customer(); // job application
		customer.setAvatar("foto");
		customer.setEmail("paco@us.es");
		customer.setName("Paco");
		customer.setPhoneNumber("692398182");
		customer.setSurname("Zamudio");
		try {
			this.customerService.save(customer);
		} catch (Exception e) {
			Logger.getLogger(CustomerServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(customer.getId()).isNotNull();
	}
}

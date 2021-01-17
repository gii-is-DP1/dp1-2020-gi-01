package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.CustomerRepository;
import com.project.TabernasSevilla.security.UserService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CustomerServiceTest {
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	@Autowired
	protected UserService userService;

	
	@Test
	public void testSetPreferredEstablishment() {
		Customer cust = new Customer();
		Establishment est = new Establishment();
		est.setId(10);
		cust = this.customerService.setPreferredEstablishment(10);
//		Customer saved = this.customerService.save(cust);
		assertThat(cust.getFavEstablishment().getId());		
	}
	
//	test register
}

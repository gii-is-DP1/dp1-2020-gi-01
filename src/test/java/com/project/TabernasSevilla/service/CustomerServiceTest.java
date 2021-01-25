package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.CustomerRepository;
import com.project.TabernasSevilla.security.UserService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CustomerServiceTest {
	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected EstablishmentService establishmentService;
	

	@Test
	public void testSetPreferredEstablishment() {
		Customer cust = new Customer();
		Establishment est = new Establishment();
		est.setTitle("prueba");
		est.setAddress("calle ");
		est.setCapacity(10);
		est.setCurrentCapacity(10);
		est.setOpeningHours("24/7");
		est.setScore(2);
		Establishment estSaved = this.establishmentService.save(est);
		cust = this.customerService.setPreferredEstablishment(estSaved.getId(), "guaito1");
		Customer saved = this.customerService.save(cust);
		assertThat(saved.getFavEstablishment().getId() == estSaved.getId());
	}

	@Test
	public void testingRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("usuario");
		form.setPassword("contraseña");
		form.setAcceptTerms(true);
		Customer regis = this.customerService.register(form);
		Customer saved = this.customerService.save(regis);
		assertThat(saved).isNotNull();
	}

	@Test
	public void testingBadRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("");
		form.setPassword("");
		form.setAcceptTerms(true);
		assertThrows(ConstraintViolationException.class, () -> {
			Customer regis = this.customerService.register(form);
			Customer saved = this.customerService.save(regis);
		});
	}
}
package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.forms.RegisterForm;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AdminServiceTest{
	@Autowired
	protected AdminService adminService;

	@Test
	public void testingRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("usuario");
		form.setPassword("contraseña");
		form.setAcceptTerms(true);
		Admin regis = this.adminService.register(form);
		Admin saved = this.adminService.save(regis);
		assertThat(saved).isNotNull();
	}
	
	@Test
	public void testingBadRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("");
		form.setPassword("");
		form.setAcceptTerms(true);
		assertThrows(ConstraintViolationException.class, ()->{
			Admin regis = this.adminService.register(form);
		Admin saved = this.adminService.save(regis);});

		
	}

}

package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.forms.RegisterForm;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CookServiceTest{
	@Autowired
	protected CookService cookService;

	@Test
	public void testingRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("usuario");
		form.setPassword("contraseña");
		form.setAcceptTerms(true);
		Cook regis = this.cookService.register(form);
		Cook saved = this.cookService.save(regis);
		assertThat(saved).isNotNull();
	}
	
	@Test
	public void testingBadRegisterandSave() {
		RegisterForm form = new RegisterForm();
		form.setUsername("");
		form.setPassword("");
		form.setAcceptTerms(true);
		assertThrows(ConstraintViolationException.class, ()->{
			Cook regis = this.cookService.register(form);
			});		
	}
}

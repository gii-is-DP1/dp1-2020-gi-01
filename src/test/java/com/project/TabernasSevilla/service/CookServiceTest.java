package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.project.TabernasSevilla.domain.Cook;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CookServiceTest{
	@Autowired
	protected CookService cookService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Cook cook = new Cook(); // job application
		cook.setAvatar("foto");
		cook.setEmail("paco@us.es");
		cook.setName("Paco");
		cook.setPhoneNumber("692398182");
		cook.setSurname("Zamudio");
		try {
			this.cookService.save(cook);
		} catch (Exception e) {
			Logger.getLogger(CookServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(cook.getId()).isNotNull();
	}

}

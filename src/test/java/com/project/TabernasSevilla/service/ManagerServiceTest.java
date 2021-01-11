package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Manager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ManagerServiceTest {
	@Autowired
	protected ManagerService managerService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Manager manager = new Manager(); // job application
		manager.setAvatar("foto");
		manager.setEmail("paco@us.es");
		manager.setName("Paco");
		manager.setPhoneNumber("692398182");
		manager.setSurname("Zamudio");
		try {
			this.managerService.save(manager);
		} catch (Exception e) {
			Logger.getLogger(ManagerServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(manager.getId()).isNotNull();
	}
}

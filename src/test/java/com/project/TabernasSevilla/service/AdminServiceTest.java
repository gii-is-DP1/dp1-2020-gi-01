package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Admin;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AdminServiceTest{
	@Autowired
	protected AdminService adminService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Admin admin = new Admin(); // job application
		admin.setAvatar("foto");
		admin.setEmail("paco@us.es");
		admin.setName("Paco");
		admin.setPhoneNumber("692398182");
		admin.setSurname("Zamudio");
		try {
			this.adminService.save(admin);
		} catch (Exception e) {
			Logger.getLogger(AdminServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(admin.getId()).isNotNull();
	}

}

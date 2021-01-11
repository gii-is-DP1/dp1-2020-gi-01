package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Curriculum;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ContactServiceTest {
	
	protected ContactService contactService;

	//This is a testing class for ContactService that stores the curriculums of the people who want to apply for a job at Tabernas Sevilla
	// found in "Work with us!"
	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Curriculum cv = new Curriculum(); //job application
		cv.setCv("cv");
		cv.setEmail("Testing@test.com");
		cv.setFullName("Mister Test");
		try {
			this.contactService.save(cv);
		} catch (Exception e) {
			Logger.getLogger(ContactServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(cv.getId()).isNotNull();
	}
}

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
//	@Test
//	register
}

package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Curriculum;
import com.project.TabernasSevilla.forms.ContactForm;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ContactServiceTest {
	
	protected ContactService contactService;

	//This is a testing class for ContactService that stores the curriculums of the people who want to apply for a job at Tabernas Sevilla
	// found in "Work with us!"
	@Test
	public void testingRegisterandSave() {
	ContactForm form = new ContactForm();	
	form.setCv("pdf");
	form.setEmail("alonso@us.es");
	form.setFullName("Alonso");
	Curriculum regis = this.contactService.register(form);
	Curriculum saved = this.contactService.save(regis);
	assertThat(saved).isNotNull();
	}
	
//	@Test
//	public void testingBlankRegisterandSave() {
//	ContactForm c = new ContactForm();	
//	c.setCv("pdf");
//	c.setEmail("");
//	c.setFullName("");
////	assertThrows(NullPointerException.class, ()->{
//		Curriculum regis = this.contactService.register(c);
//		Curriculum saved = this.contactService.save(regis);
//	}
}

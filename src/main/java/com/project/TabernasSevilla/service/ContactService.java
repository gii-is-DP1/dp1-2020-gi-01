package com.project.TabernasSevilla.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.TabernasSevilla.domain.JobApplication;
import com.project.TabernasSevilla.forms.ContactForm;
import com.project.TabernasSevilla.repository.ContactRepository;


@Service
@Transactional
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepo;
	
	public JobApplication findById(final int id) { //hmmm no hay id en jobapplication
		return contactRepo.findById(id);
	}
	
	public JobApplication create() {
		JobApplication joba = new JobApplication();
		return joba;	
	}
	
	public JobApplication save(JobApplication joba) {
		return this.contactRepo.save(joba);
	}
	
	public JobApplication register(final ContactForm form) {
		JobApplication joba = create();
		joba.setFullName(form.getFullName());
		joba.setEmail(form.getEmail());
		joba.setCv(form.getCv());
		JobApplication jobaded = save(joba);
		return jobaded;
	}
}

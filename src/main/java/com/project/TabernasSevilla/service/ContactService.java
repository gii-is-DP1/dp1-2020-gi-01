package com.project.TabernasSevilla.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Curriculum;
import com.project.TabernasSevilla.forms.ContactForm;
import com.project.TabernasSevilla.repository.CurriculumRepository;


@Service
@Transactional

//job application / curriculum service
public class ContactService {
	
 
	private CurriculumRepository cvRepo;
	
	@Autowired
	public ContactService(CurriculumRepository cvRepo) {
		super();
		this.cvRepo = cvRepo;
	}

	public Curriculum findById(final int id) { //hmmm no hay id en jobapplication o si...
		return cvRepo.findById(id);
	}
	
	public Curriculum create() {
		Curriculum joba = new Curriculum();
		return joba;	
	}
	
	public Curriculum save(Curriculum joba) {
		return this.cvRepo.save(joba);
	}
	
	public Curriculum register(final ContactForm form) {
		Curriculum joba = create();
		joba.setFullName(form.getFullName());
		joba.setEmail(form.getEmail());
		joba.setCv(form.getCv());
		Curriculum jobaded = save(joba);
		return jobaded;
	}
}

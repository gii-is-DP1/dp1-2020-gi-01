package com.project.TabernasSevilla.service;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
		return this.cvRepo.saveAndFlush(joba);
	}
	
	public Curriculum register(final ContactForm form) {
		Curriculum joba = create();
		joba.setFullName(form.getFullName());
		joba.setEmail(form.getEmail());
		joba.setCv(form.getCv());
		try {

			write(form.getCv(),Paths.get("src/main/resources/JobApplications"));
			System.out.println("========GUARDADO EL CV CORRECTAMENTE=========");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		Curriculum jobaded = save(joba);
		return jobaded;
	}
	
	//guardar el CV localmente en la carpeta de resources/JobApplications
	public void write(MultipartFile file, Path dir) {
	    Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());

	    try (OutputStream os = Files.newOutputStream(filepath)) {
	        os.write(file.getBytes());
	    }catch(final Exception e) {
	    	e.printStackTrace();
	    }
	}
}

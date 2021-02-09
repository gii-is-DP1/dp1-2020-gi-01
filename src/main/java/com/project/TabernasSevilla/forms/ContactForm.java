package com.project.TabernasSevilla.forms;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactForm { 
	
	
	@NotEmpty(message = "You must put your name and surname to contact you, thank you.")
	@Size(min = 2, max = 128)
	private String fullName;
	
	@NotNull
	@NotEmpty(message = "The field must be correctly filled in") 
	@Email(message = "Please enter a valid email address.")
	private String email;
	
	@NotNull
	@Transient
	private MultipartFile cv;

}

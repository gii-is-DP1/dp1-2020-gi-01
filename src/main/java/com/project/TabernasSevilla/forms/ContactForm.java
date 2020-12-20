package com.project.TabernasSevilla.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactForm { 
	
	//est - validaciones hechas.
	
	@NotEmpty(message = "You must put your name and surname to contact you, thank you.")
	@Size(min = 2, max = 128)
	//To allow "tildes"
	@Pattern(regexp ="[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]" 
	, message = "Please enter your first and last name correctly.")
	private String fullName;
	
	@NotEmpty(message = "The field must be correctly filled in") 
	@Email(message = "Please enter a valid email address.")
	private String email;
	
	private String cv;

}

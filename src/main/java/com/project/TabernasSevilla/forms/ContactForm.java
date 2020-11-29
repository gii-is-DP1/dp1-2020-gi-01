package com.project.TabernasSevilla.forms;

import lombok.Data;

@Data
public class ContactForm { //no hay validaciones porque se pueden poner en el html 
	
	private String fullName;
	private String email;
	private String cv;

}

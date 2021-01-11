package com.project.TabernasSevilla.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

public class ActorForm {

	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@Size(min = 4, max = 32, message = "username must be between 4 and 32 characters.")
	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Characters must be alphanumeric")
	private String username;
	
	//@Pattern(regexp="^[9|8|7|6]\d{8}$", message="Phone number must start from 9, 8, 7 or 6.")
//	Pattern pattern = Pattern.compile("^(\+34|0034|34)?[6789]\d{8}$");
//	fijos = Pattern pattern = Pattern.compile("^(\+34|0034|34)?[89]\d{8}$");
//	moviles = Pattern pattern = Pattern.compile("^(\+34|0034|34)?[67]\d{8}$");
	@Size(min = 9, max = 9, message = "Not a valid number.")
	private String phoneNumber;
	@Email
	private String email;
	
	private String avatar;
	
	public ActorForm() {
		super();
	}

//	@Size(min = 4, max = 32, message = "Username must have 4 to 32 characters")
//	@Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Characters must be alphanumeric")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@URL
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(final String avatar) {
		this.avatar = avatar;
	}
}

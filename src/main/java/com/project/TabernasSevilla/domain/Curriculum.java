package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
public class Curriculum extends BaseEntity {
	
	
	@NotBlank
	private String fullName;	
	@NotBlank
	@Email
	private String email;

	private String cv;

}

package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity
public class JobApplication extends BaseEntity {
	
	
	@NotBlank
	private String fullName;	
	@NotBlank
	private String email;
	
	private String cv;

}

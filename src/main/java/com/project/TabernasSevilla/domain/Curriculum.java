package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
	
	@Transient
	private MultipartFile cv;

}

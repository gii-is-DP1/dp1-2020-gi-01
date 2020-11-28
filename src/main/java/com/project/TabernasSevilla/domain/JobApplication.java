package com.project.TabernasSevilla.domain;

import javax.validation.constraints.NotBlank;

import com.project.TabernasSevilla.security.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JobApplication extends BaseEntity {
	
	@NotBlank
	private String fullName;
	
	@NotBlank
	private String email;
	
	private String cv;

}

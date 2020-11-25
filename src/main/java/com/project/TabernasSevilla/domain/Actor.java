package com.project.TabernasSevilla.domain;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.project.TabernasSevilla.security.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Actor extends BaseEntity{
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName="id")
	@MapsId
	private User user;
	
	@NotBlank
	private  String name;
	
	@NotBlank
	private String surname;
	
	@URL
	private String avatar;
	
	@Email
	private String email;
	
	private  String phoneNumber;
}

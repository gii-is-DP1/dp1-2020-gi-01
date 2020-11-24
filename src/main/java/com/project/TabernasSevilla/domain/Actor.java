package com.project.TabernasSevilla.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@MappedSuperclass
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Actor extends BaseEntity{
	
	
	private
	@OneToOne(cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name = "username", referencedColumnName = "username")
	User user;
	
	
	private @NotBlank @Column(name="name") String name;
	
	private @NotBlank  @Column(name="surname") String surname;
	
	private @URL  @Column(name="avatar") String avatar;
	
	private @Email  @Column(name="email") String email;
	
	private  @Column(name="phone_number") String phoneNumber;
}

package com.project.TabernasSevilla.security;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import com.project.TabernasSevilla.domain.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {

	// Constructors -----------------------------------------------------------

	private static final long serialVersionUID = 1L;

	public Authority() {
		super();
	}

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String authority;

}

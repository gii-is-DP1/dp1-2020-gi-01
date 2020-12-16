package com.project.TabernasSevilla.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.project.TabernasSevilla.security.Authority;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RegKey {
	@Id
	@Column(unique = true)
	private String key;
	@NotNull
	@ManyToOne
	private Authority authority;
}

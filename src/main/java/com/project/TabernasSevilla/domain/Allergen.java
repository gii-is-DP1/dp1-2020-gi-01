package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Allergen extends BaseEntity{
	@NotBlank
	private String name;
	@NotBlank
	private String abbreviation;
	@NotBlank
	private String icon;
}

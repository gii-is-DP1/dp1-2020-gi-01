package com.project.TabernasSevilla.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Dish extends BaseEntity {

	@NotBlank
	@NotNull
	private String name;

	@NotBlank
	@NotNull
	private String description;

	@NotBlank
	@NotNull
	@URL
	private String picture;

	@Min(0)
	private Double price;

	@Min(0)
	@Max(5)
	private Double score;

	private Boolean isVisible;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Allergen> allergens;

}

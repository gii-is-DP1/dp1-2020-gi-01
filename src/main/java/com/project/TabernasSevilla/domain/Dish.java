package com.project.TabernasSevilla.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

public class Dish extends BaseEntity {

	@NotBlank
	private String name;
	private String description;
	private String picture;
	private Double price;
	@Min(0)
	@Max(5)
	private Double score;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Allergen> allergens;
	
	//@OneToMany
	//private List<Review> reviews;

}

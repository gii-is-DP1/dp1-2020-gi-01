package com.project.TabernasSevilla.domain;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Dish extends NamedEntity{
	

		private String name;
		private String description;
		private String picture;
		private Double price;
		private Double score;
		private String allergens;

	}


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
public class Establishment extends BaseEntity {

	@NotBlank
	private String title;

	private String description;

	@URL
	private String picture;
	@NotBlank
	private String address;

	private String phone;
	@NotBlank
	private String openingHours;
	
	@Min(0) @Max(5)@NotNull
	private Integer score;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Manager> manager;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Waiter> waiter;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Cook> cook;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Dish> dish;
}

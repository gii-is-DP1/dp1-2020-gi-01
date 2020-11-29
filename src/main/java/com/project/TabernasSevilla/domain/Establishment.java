package com.project.TabernasSevilla.domain;


import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
	
	@NotBlank @Min(1)
	private Integer capacity;
	@NotBlank
	private Integer currentCapacity;

	private String phone;
	@NotBlank
	private String openingHours;
	@Min(1) @Max(5)@NotBlank
	private Integer score;
}

package com.project.TabernasSevilla.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
public class RestaurantTable extends BaseEntity {

	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	private Establishment establishment;
	
	@NotBlank
	private Integer number;
	@NotBlank
	private Integer seating;
	@NotBlank
	private Integer occupied;

	@NotBlank
	private LocalDateTime hourSeated;
	
}

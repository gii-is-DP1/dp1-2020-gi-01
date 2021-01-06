package com.project.TabernasSevilla.domain;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	private Integer number;
	@NotNull
	private Integer seating;
	@NotNull
	private Integer occupied;

	private Instant hourSeated;
	
}

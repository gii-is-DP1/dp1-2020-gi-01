package com.project.TabernasSevilla.domain;


import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@OneToOne(optional=true,fetch = FetchType.LAZY)
	private Booking booking;
	
	@NotNull
	private Integer number;
	@NotNull
	private Integer seating;
	@NotNull
	private Integer occupied;

	private Instant hourSeated;
	
}

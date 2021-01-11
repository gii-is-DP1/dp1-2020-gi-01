package com.project.TabernasSevilla.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
public class Booking extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	private Actor actor;
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	private Establishment establishment;

	@NotNull
	private Instant placementDate;
	@NotNull
	private Instant reservationDate;
	@NotNull
	private Integer seating;

	private String contactPhone;

	private String notes;
}

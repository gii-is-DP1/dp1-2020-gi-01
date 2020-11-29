package com.project.TabernasSevilla.domain;

import java.time.LocalDateTime;

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
public class Booking extends BaseEntity {

	@NotBlank
	private LocalDateTime placementDate;
	@NotBlank
	private LocalDateTime reservationDate;

	@NotBlank
	private Integer seating;

	private String contactPhone;
	
	private String notes;
}

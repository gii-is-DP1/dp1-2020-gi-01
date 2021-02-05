package com.project.TabernasSevilla.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderLog extends BaseEntity {

	@ManyToOne(optional=false,fetch = FetchType.LAZY)
	private RestaurantOrder order;
	@NotNull
	private Instant moment;
	@NotBlank
	private String status;



}

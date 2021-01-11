package com.project.TabernasSevilla.domain;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

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
	@Past
	@NotNull
	private Instant moment;
	@NotBlank
	private String status;



}

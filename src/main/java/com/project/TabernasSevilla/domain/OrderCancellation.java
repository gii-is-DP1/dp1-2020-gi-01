package com.project.TabernasSevilla.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
public class OrderCancellation extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	private RestaurantOrder order;
	@NotNull
	private LocalDateTime placementDate;
	@NotBlank
	private String reason;
}

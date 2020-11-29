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
public class Table extends BaseEntity {

	@NotBlank
	private Integer number;
	@NotBlank
	private Integer seating;

	@NotBlank
	private LocalDateTime hourSeated;
	
}

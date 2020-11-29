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
public class Message extends BaseEntity {

	private String title;
	@NotBlank
	private LocalDateTime deliveryDate;

	private LocalDateTime readDate;
	
	private String body;
}

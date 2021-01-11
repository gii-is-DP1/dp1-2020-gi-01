package com.project.TabernasSevilla.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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

	// relations
	@ManyToOne(fetch=FetchType.LAZY)
	private Actor recipient;
	@ManyToOne(fetch=FetchType.EAGER)
	private MessageBox container;
	// attributes
	@NotBlank
	private String title;
	@Past
	@NotNull
	private LocalDateTime deliveryDate;
	private String body;
}

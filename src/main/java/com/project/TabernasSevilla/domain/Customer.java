package com.project.TabernasSevilla.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Setter
public class Customer extends Actor{

	@ManyToOne(optional = true,fetch = FetchType.EAGER)
	private Establishment favEstablishment;
}

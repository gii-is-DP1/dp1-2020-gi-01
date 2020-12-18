package com.project.TabernasSevilla.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

	@ManyToOne(optional=true,fetch=FetchType.EAGER)
	private Actor actor;
	@ManyToOne(optional=true,fetch=FetchType.EAGER)
	private Booking booking;
	@ManyToOne(optional=true,fetch=FetchType.EAGER)
	private Table table;
	
	@NotNull
	@Past
	private LocalDateTime placementDate;
	
	@NotBlank
	@Pattern(regexp = "^" + Order.DELIVERY + "|" + Order.TAKEAWAY + "|" + Order.PICKUP + "|"
			+ Order.EAT_IN + "$")
	private String type;

	@ManyToMany
	private List<Dish> dish;

	private String address;
	@NotBlank
	private Boolean active;

	public static final String TAKEAWAY = "TAKEAWAY";
	public static final String PICKUP = "PICKUP";
	public static final String DELIVERY = "DELIVERY";
	public static final String EAT_IN = "EAT-IN";

}

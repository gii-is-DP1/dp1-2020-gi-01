package com.project.TabernasSevilla.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
	private LocalDateTime placementDate;
	@NotBlank
	@Pattern(regexp = "^" + Order.TYPE_DELIVERY + "|" + Order.TYPE_TAKEAWAY + "|" + Order.TYPE_PICKUP + "|"
			+ Order.TYPE_EAT_IN + "$")
	private String type;
	//TODO: pattern validation
	@NotBlank
	private String status;

	private String address;
	@NotBlank
	private Boolean active;

	public static final String TYPE_TAKEAWAY = "TAKEAWAY";
	public static final String TYPE_PICKUP = "PICKUP";
	public static final String TYPE_DELIVERY = "DELIVERY";
	public static final String TYPE_EAT_IN = "EAT-IN";

	public static final String STATUS_OPEN = "OPEN";
	public static final String STATUS_CLOSED = "CLOSED";
	public static final String STATUS_CANCELLED = "CANCELLED";
}

package com.project.TabernasSevilla.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	private Order order;
	@Past
	@NotNull
	private LocalDateTime moment;
	@NotBlank
	@Pattern(regexp = "^" + OrderLog.OPEN + "|" + OrderLog.CLOSED + "|" + OrderLog.PLACED + "|" + OrderLog.PREP + "|"
			+ OrderLog.READY + "|" + OrderLog.DELIVERY + "|" + OrderLog.CANCELLED + "$")
	private String status;

	public static final String OPEN = "OPEN";
	public static final String CLOSED = "CLOSED";
	public static final String PLACED = "ORDER PLACED";
	public static final String PREP = "PREPERING";
	public static final String READY = "READY";
	public static final String DELIVERY = "OUT FOR DELIVERY";
	public static final String DELIVERED = "DELIVERED";
	public static final String CANCELLED = "CANCELLED";

}

package com.project.TabernasSevilla.domain;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
public class RestaurantOrder extends BaseEntity {

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	private Actor actor;
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	private Establishment establishment;
	@ManyToOne(optional=true,fetch=FetchType.EAGER)
	private RestaurantTable table;
	
	
	private Instant placementDate;
	
	@NotBlank
	@Pattern(regexp = "^" + RestaurantOrder.DELIVERY + "|" + RestaurantOrder.TAKEAWAY + "|" + RestaurantOrder.PICKUP + "|"
			+ RestaurantOrder.EAT_IN + "$")
	private String type;

	@ManyToMany
	private List<Dish> dish;

	private String address;

	@NotNull
	@Pattern(regexp = "^" + RestaurantOrder.OPEN + "|" + RestaurantOrder.CLOSED + "|" + RestaurantOrder.PLACED + "|" + RestaurantOrder.PREP + "|"
			+ RestaurantOrder.READY + "|" + RestaurantOrder.STS_DELIVERY + "|" + RestaurantOrder.CANCELLED + "|" + RestaurantOrder.DRAFT+ "$")
	private String status;

	public List<String> getTypes(){
		return Arrays.asList(RestaurantOrder.TAKEAWAY,RestaurantOrder.PICKUP,RestaurantOrder.DELIVERY,RestaurantOrder.EAT_IN);
	}
	
	public List<String> getOnlineTypes(){
		return Arrays.asList(RestaurantOrder.PICKUP,RestaurantOrder.DELIVERY);
	}

	
	public static final String DRAFT = "DRAFT";
	
	public static final String TAKEAWAY = "TAKEAWAY";
	public static final String PICKUP = "PICKUP";
	public static final String DELIVERY = "DELIVERY";
	public static final String EAT_IN = "EAT-IN";
	
	public static final String OPEN = "OPEN";
	public static final String CLOSED = "CLOSED";
	public static final String PLACED = "ORDER PLACED";
	public static final String PREP = "PREPARING";
	public static final String READY = "READY";
	public static final String STS_DELIVERY = "OUT FOR DELIVERY";
	public static final String DELIVERED = "DELIVERED";
	public static final String CANCELLED = "CANCELLED";
	public static final List<String> DeliveryStatus = Arrays.asList(RestaurantOrder.PLACED,RestaurantOrder.PREP,RestaurantOrder.READY,RestaurantOrder.STS_DELIVERY,RestaurantOrder.DELIVERED);
	public static final List<String> PickupStatus = Arrays.asList(RestaurantOrder.PLACED,RestaurantOrder.PREP,RestaurantOrder.READY);
	public static final List<String> EatInStatus = Arrays.asList(RestaurantOrder.OPEN,RestaurantOrder.CLOSED);

}

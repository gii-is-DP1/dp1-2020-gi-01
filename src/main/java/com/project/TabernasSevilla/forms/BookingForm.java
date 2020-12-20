package com.project.TabernasSevilla.forms;

import java.time.LocalDateTime;

import com.project.TabernasSevilla.domain.Establishment;

import lombok.Data;


@Data
public class BookingForm {
	
	private LocalDateTime placementDate;
	private LocalDateTime reservationDate;
	private Integer seating;
	private String contactPhone;
	private String notes;
	private Establishment location;
	//aqui hay que añadir un pedido que es opcional
	// Y LOCATION

}

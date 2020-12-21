package com.project.TabernasSevilla.forms;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.TabernasSevilla.domain.Establishment;

import lombok.Data;


@Data
public class BookingForm {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime placementDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime reservationDate;
	private Integer seating;
	private String hourDate;
	
	private String contactPhone;
	
	private String notes;
	private Establishment location;
	//aqui hay que añadir un pedido que es opcional
	// Y LOCATION
	

}

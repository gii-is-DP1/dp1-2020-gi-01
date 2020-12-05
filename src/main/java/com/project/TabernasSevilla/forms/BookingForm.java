package com.project.TabernasSevilla.forms;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class BookingForm {
	
	private LocalDateTime placementDate;
	private LocalDateTime reservationDate;
	private Integer seating;
	private String contactPhone;
	private String notes;

}

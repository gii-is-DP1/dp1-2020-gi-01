package com.project.TabernasSevilla.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.forms.BookingForm;
import com.project.TabernasSevilla.repository.BookingRepository;

@Service
@Transactional
public class BookingService {
	
	@Autowired
	private BookingRepository repo;
	
	public Booking findById(final int id) {
		return repo.findById(id);
	}

	public Booking create() {
		Booking boka = new Booking();
		return boka;
	}
	
	public Booking save(Booking boka) {
		return repo.save(boka);
	}
	
	public Booking register(final BookingForm forma) {
		Booking boka = create();
		boka.setPlacementDate(forma.getPlacementDate());
		boka.setReservationDate(forma.getReservationDate());
		boka.setContactPhone(forma.getContactPhone());
		//TODO: fix to accomodate for multiple notes or change to have just one note 
		//boka.setNotes(forma.getNotes());
		boka.setSeating(forma.getSeating());
		Booking bokaed = save(boka);
		return bokaed;
	}
}

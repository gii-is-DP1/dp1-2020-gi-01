package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;
import com.project.TabernasSevilla.forms.BookingForm;
import com.project.TabernasSevilla.repository.BookingRepository;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private ActorService actorService;
	@Autowired
	private TableService tableService;
	
	//CRUD
	
	public Optional<Booking> findById(final int id) {
		return this.bookingRepo.findById(id);
	}
	
	public List<Booking> findAll() {
		return this.bookingRepo.findAll();
	}
	
	public void delete(Booking booking) {
		this.bookingRepo.delete(booking);
	}

	public Booking create() {
		Booking boka = new Booking();
		return boka;
	}
	
	public Booking save(Booking boka) {
		return bookingRepo.save(boka);
	}
	
	//OTHER METHODS
	
	//TODO: return all for establishment
	public List<Booking> findByEstablishment(Establishment est){
		return this.bookingRepo.findByEstablishment(est.getId());
	}
	
	public List<Booking> findUnallocatedByEstablishment(Establishment est){
		List<Booking> bookings = this.findByEstablishment(est);
		List<RestaurantTable> tables = this.tableService.findBooked(est);
		for(RestaurantTable t:tables) {
			if(bookings.contains(t.getBooking())) {
				bookings.remove(t.getBooking());
			}
		}	
		return bookings;
	}
	
	//TODO: return all for establishment where reservation date > today's date
	
	public Booking initialize(Establishment est) {
		Booking res = this.create();
		res.setActor(this.actorService.getPrincipal());
		res.setPlacementDate(Instant.now());
		res.setEstablishment(est);
		return res;
	}
	
	public Booking register(Booking booking) {
		Instant free = this.tableService.estimateFreeTableInstant(booking.getEstablishment());
		Instant min = Instant.now().plus(2,ChronoUnit.HOURS);
		Assert.isTrue(free.compareTo(booking.getReservationDate())<0,"Cannot book for this time: restaurant is too busy");
		Assert.isTrue(min.compareTo(booking.getReservationDate())<0,"Cannot book for this time: booking notice too short");
		booking.setActor(this.actorService.getPrincipal());
		booking.setPlacementDate(Instant.now());
		Booking saved = this.save(booking);
		return saved;
	}
	
//	public Booking register(final BookingForm forma) {
//		Booking boka = create();
//		boka.setPlacementDate(forma.getPlacementDate());
//		boka.setReservationDate(forma.getReservationDate());
//		boka.setContactPhone(forma.getContactPhone());
//		//TODO: fix to accomodate for multiple notes or change to have just one note 
//		//boka.setNotes(forma.getNotes());
//		boka.setSeating(forma.getSeating());
//		boka.setHourDate(forma.getHourDate());
//		boka.setEstablishment(forma.getLocation());
//		Booking bokaed = save(boka);
//		return bokaed;
//	}
}

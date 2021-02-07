package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;
import com.project.TabernasSevilla.repository.BookingRepository;

@Service
@Transactional
public class BookingService {
	
 
	private BookingRepository bookingRepo;
 
	private ActorService actorService;
 
	private TableService tableService;
	
	
	@Autowired
	public BookingService(BookingRepository bookingRepo, ActorService actorService, TableService tableService) {
		super();
		this.bookingRepo = bookingRepo;
		this.actorService = actorService;
		this.tableService = tableService;
	}

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
	
	public List<Booking> findByPrincipalActor(){
		return this.bookingRepo.findByActor(this.actorService.getPrincipal().getId());
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
	
	public Booking register(Booking booking, Actor actor) {
		Instant free = this.tableService.estimateFreeTableInstant(booking.getEstablishment());
		Instant min = Instant.now().plus(2,ChronoUnit.HOURS);
		
		//comprueba si el restaurante está lleno en el mismo día
		//TODO: esto no tiene sentido
		//Assert.isTrue(!((tableService.getOccupancyAtRestaurant((booking.getEstablishment())) == (long) booking.getEstablishment().getCapacity()) && 
		//		(booking.getReservationDate().atZone(ZoneId.systemDefault()).getDayOfWeek() == Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek())), "The restaurant is currently full, sorry for the inconvenience");
		
		Assert.isTrue(free.compareTo(booking.getReservationDate())<0,"Cannot book for this time: restaurant is too busy");
		Assert.isTrue(min.compareTo(booking.getReservationDate())<0,"Cannot book for this time: booking notice too short");
		booking.setActor(actor);
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

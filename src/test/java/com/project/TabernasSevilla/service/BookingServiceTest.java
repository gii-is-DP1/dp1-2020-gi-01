package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class BookingServiceTest{
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private EstablishmentService estService;
//
//	@Test
//	public void testingRegisterandSave() {
//		Booking b = new Booking();	
//		Establishment est = new Establishment();
//		est.setCapacity(100);
//		est.setCurrentCapacity(10);
//		Establishment saved = this.estService.save(est);
//		b.setActor(new Admin());
//		b.setContactPhone("655778899");
//		b.setEstablishment(est);
//		b.setNotes("Comida de navidad");
//		b.setPlacementDate(Instant.now());
////		b.setReservationDate(Instant.now().plus(Duration.ofDays(20)));
//		b.setSeating(4);
//		Booking regis = this.bookingService.register(b);
////		Booking saved = this.bookingService.save(regis);
////		assertThat(saved).isNotNull();
//	}
	
	@Test
	public void testingNullRegisterandSave() {
		Booking b = new Booking();	
		b.setActor(new Admin());
		b.setContactPhone("655778899");
		b.setEstablishment(new Establishment());
		b.setNotes("Comida de navidad");
		b.setPlacementDate(null);
		b.setReservationDate(null);
		b.setSeating(4);
//		Las fechas no pueden ser null
		assertThrows(NullPointerException.class, ()->{
			Booking regis = this.bookingService.register(b);
			Booking saved = this.bookingService.save(regis);});		
	}
	
	@Test
	public void testingBadRegisterandSave() {
		Booking b = new Booking();	
		b.setActor(new Admin());
		b.setContactPhone("655778899");
		b.setEstablishment(new Establishment());
		b.setNotes("Comida de navidad");
		b.setPlacementDate(Instant.now());
		b.setReservationDate(Instant.now());
		b.setSeating(4);
//		No puedo reservar con menos de dos horas de antelación
		assertThrows(IllegalArgumentException.class, ()->{
			Booking regis = this.bookingService.register(b);
			Booking saved = this.bookingService.save(regis);});		
	}

}

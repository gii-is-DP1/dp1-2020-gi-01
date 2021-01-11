package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Booking b = new Booking();
		Admin a = new Admin();
		Establishment es = new Establishment();
		b.setActor(a);
		b.setContactPhone("655443344");
		b.setEstablishment(es);
		b.setPlacementDate(Instant.now());
		b.setReservationDate(Instant.now());
		b.setNotes("Una mesita bien limpita");
		b.setSeating(8);
		try {
			this.bookingService.save(b);
		} catch (Exception e) {
			Logger.getLogger(BookingServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(b.getId()).isNotNull();
	}

}

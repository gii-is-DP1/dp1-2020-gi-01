package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class TableServiceTest {
	@Autowired
	protected TableService tableService;

	@Autowired
	protected EstablishmentService estService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private AdminService adminService;

	@Test
	public void testEstimateFreeTable() {
		// Instant.now muestra 1 hora menos de la actual debido al uso horario.
		Instant res = null;
		Establishment est = this.estService.findById(1);
		RestaurantTable table = this.tableService.findById(1);
		table.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		RestaurantTable table1 = this.tableService.findById(2);
		table1.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		RestaurantTable table2 = this.tableService.findById(3);
		table2.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		res = this.tableService.estimateFreeTableInstant(est);
		// Hago que todas las mesas estén reservadas hasta dentro de 2 horas
		// Después compruebo que al hacer la estimación, mínimo será dentro de 2 horas
		assertThat(res).isBeforeOrEqualTo(Instant.now().plus(Duration.ofHours(2)));
	}

	@Test
	public void shouldFindTablesBooked() {
		Booking b = new Booking();
		b.setActor(this.adminService.findAll().get(0));
		b.setContactPhone("655778899");
		b.setEstablishment(this.estService.findById(1));
		b.setNotes("Comida de navidad");
		b.setPlacementDate(Instant.now());
		b.setReservationDate(Instant.now().plus(Duration.ofDays(2)));
		b.setSeating(4);
		this.bookingService.register(b, this.adminService.findAll().get(0));
		RestaurantTable table = this.tableService.findById(1);
		table.setBooking(b);
		List<RestaurantTable> list = this.tableService.findBooked(this.estService.findById(1));
		assertThat(list).hasSize(1);
	}

	@Test
	public void shouldGenerateNumber() {
		Integer number = this.tableService.generateNumber(this.estService.findById(1));
		//Es igual a 4 porque ya hay 3 mesas creadas en la base de datos.
		assertThat(number).isEqualTo(4);
	}

	@Test
	public void shouldGetOccupancyAtRestaurant() {
		RestaurantTable table = this.tableService.findById(1);
		table.setOccupied(5);
		Long occupancy = this.tableService.getOccupancyAtRestaurant(this.estService.findById(1));
		assertThat(occupancy).isEqualTo(5);
	}

	@Test
	public void shouldCountFreeTables() {
		Long number = this.tableService.countFreeTables(this.estService.findById(1));
		assertThat(number).isEqualTo(3);
	}
}

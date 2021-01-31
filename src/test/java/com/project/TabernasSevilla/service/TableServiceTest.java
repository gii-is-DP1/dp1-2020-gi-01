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

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.RestaurantTable;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class TableServiceTest {
	@Autowired
	protected TableService tableService;
	
	@Autowired
	protected EstablishmentService estService;

	@Test
	public void testEstimateFreeTable(){
		//Instant.now muestra 1 hora menos de la actual debido al uso horario.
		Instant res = null;
		Establishment est = this.estService.findById(1);
		RestaurantTable table =this.tableService.findById(1);
		table.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		RestaurantTable table1 =this.tableService.findById(2);
		table1.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		RestaurantTable table2 =this.tableService.findById(3);
		table2.setHourSeated(Instant.now().plus(Duration.ofHours(2)));
		res = this.tableService.estimateFreeTableInstant(est);
		// Hago que todas las mesas estén reservadas hasta dentro de 2 horas
		// Después compruebo que al hacer la estimación, mínimo será dentro de 2 horas
		assertThat(res).isBeforeOrEqualTo(Instant.now().plus(Duration.ofHours(2)));
	}
}

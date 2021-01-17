package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

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

//	@Test
//	estimatefreetable
}

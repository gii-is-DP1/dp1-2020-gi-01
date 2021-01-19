package com.project.TabernasSevilla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

class OrderServiceTest {
	@Autowired
	protected OrderService orderService;

//	@Test
//	adddish
//	removedish
//	checkOwnership
//	calculateTotal
//	updateStatus
}

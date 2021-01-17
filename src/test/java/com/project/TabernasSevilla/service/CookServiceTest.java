package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.project.TabernasSevilla.domain.Cook;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class CookServiceTest{
	@Autowired
	protected CookService cookService;

//	@Test
//	register
}

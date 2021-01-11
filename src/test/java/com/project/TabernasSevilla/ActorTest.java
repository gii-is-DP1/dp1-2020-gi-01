package com.project.TabernasSevilla;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.RegKey;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.RegKeyService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ActorTest {

	@Autowired
	private ActorService actorService;
	@Autowired
	private RegKeyService regKeyService;
	
	@Test
	public void testRegister() {
		RegKey regKey = this.regKeyService.create();
	}
}

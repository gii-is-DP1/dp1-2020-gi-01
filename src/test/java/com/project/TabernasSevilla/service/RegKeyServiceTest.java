package com.project.TabernasSevilla.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.RegKey;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class RegKeyServiceTest {
	
	@Autowired
	protected RegKeyService regKey;
	
	@Autowired
	protected AuthorityService authService;
	
	@Autowired
	protected AdminService adminService;
	
	@MockBean
	protected UserService userService;
	
	@Test
	public void createRegKeyCook() {
		given(this.userService.getPrincipal()).willReturn(this.adminService.findAll().get(0).getUser()); //porque esta acción necesita privilegios
		
		RegKey kei = this.regKey.create();
		Authority auth = this.authService.findByName("COOK");
		kei.setAuthority(auth);
		RegKey saved = this.regKey.save(kei);
		assertThat(saved.getAuthority()).isEqualTo(this.authService.findByName("COOK"));
		assertThat(regKey.checkKey(kei.getKey())).isEqualTo(true);
	}
	
	@Test
	public void deleteRegKeyCook() {
		given(this.userService.getPrincipal()).willReturn(this.adminService.findAll().get(0).getUser()); //porque esta acción necesita privilegios
		
		RegKey kei = this.regKey.create();
		Authority auth = this.authService.findByName("COOK");
		kei.setAuthority(auth);
		RegKey saved = this.regKey.save(kei);
		this.regKey.delete(kei.getKey());
		assertThat(regKey.checkKey(kei.getKey())).isEqualTo(false);
	}
	
}
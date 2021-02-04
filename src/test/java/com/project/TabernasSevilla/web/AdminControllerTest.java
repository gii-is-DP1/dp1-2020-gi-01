package com.project.TabernasSevilla.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.AdminController;
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Seccion;
import com.project.TabernasSevilla.repository.*;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@WebAppConfiguration
@WebMvcTest(controllers = AdminController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class AdminControllerTest {
	
//	private static final int TEST_DISH_ID = 1;

	//@Autowired
	//private DishController dishController;

	@MockBean
	private UserService userService;

	@MockBean
	private ActorService actorService;

	@MockBean
	private DishService dishService;

	@MockBean
	private EstablishmentService establishmentService;
	
	@MockBean
	private AuthorityService authService;

	@MockBean
	private ReviewRepository reviewRepository;

	@MockBean
	private AuthorityRepository authRepository;

	@MockBean
	private AdminRepository adminRepository;

	@MockBean
	private BookingRepository bookingRepository;

	@MockBean
	private EstablishmentRepository establishmentRepository;

	@MockBean
	private TableRepository tableRepository;

	@MockBean
	private CurriculumRepository cur;

	@MockBean
	private CookRepository cook;

	@MockBean
	private CustomerRepository cus;

	@MockBean
	private OrderRepository ord;

	@MockBean
	private RegKeyRepository reg;

	@MockBean
	private WaiterRepository wait;

	@MockBean
	private ManagerRepository man;

	@MockBean
	private OrderCancellationRepository canc;

	@MockBean
	private OrderLogRepository logga;

	// POR ALGUN MOTIVO HE TENIDO QUE CREAR TODOS ESTOS MOCKBEANS PARA QUE FUNCIONE
	// EL TEST SIMPLE DE HTTPRESPONSE

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
//		Admin admin = new Admin();
//		admin.setAvatar("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.abc.es%2Fplay%2Fcine%2Fnoticias%2Fabci-comienza-rodaje-secuela-avatar-estrenara-once-anos-despues-cinta-original-201709281033_noticia.html&psig=AOvVaw0cgfB36sMnAaXHIO1Fj6uA&ust=1612521722925000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCKCI8dSF0O4CFQAAAAAdAAAAABAD");
//		admin.setEmail("admin@us.es");
//		admin.setId(1);
//		admin.setName("Adrian");
//		admin.setPhoneNumber("655338909");
//		admin.setSurname("Perez");
//		adminRepository.save(admin);		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testControlPanel() throws Exception {
		mockMvc.perform(get("/admin/control")).andExpect(status().isOk()).andExpect(view().name("admin/controlpanel"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key")).andExpect(status().isOk()).andExpect(view().name("admin/employees/keys"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateManagerKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key/manager")).andExpect(status().isOk()).andExpect(view().name("admin/employees/key"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateCookKey() throws Exception {
		mockMvc.perform(get("/admin/employees/key/cook")).andExpect(status().isOk()).andExpect(view().name("admin/employees/key"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateWaiterKey() throws Exception {
		mockMvc.perform(get("/admin//employees/key/waiter")).andExpect(status().isOk()).andExpect(view().name("admin/employees/key"));
	}
	
}

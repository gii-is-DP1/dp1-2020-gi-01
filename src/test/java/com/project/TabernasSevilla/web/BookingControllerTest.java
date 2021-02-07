package com.project.TabernasSevilla.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.Context;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.BookingController;
import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.AbstractActorRepository;
import com.project.TabernasSevilla.repository.AdminRepository;
import com.project.TabernasSevilla.repository.BookingRepository;
import com.project.TabernasSevilla.repository.CookRepository;
import com.project.TabernasSevilla.repository.CurriculumRepository;
import com.project.TabernasSevilla.repository.CustomerRepository;
import com.project.TabernasSevilla.repository.EstablishmentRepository;
import com.project.TabernasSevilla.repository.ManagerRepository;
import com.project.TabernasSevilla.repository.OrderCancellationRepository;
import com.project.TabernasSevilla.repository.OrderLogRepository;
import com.project.TabernasSevilla.repository.OrderRepository;
import com.project.TabernasSevilla.repository.RegKeyRepository;
import com.project.TabernasSevilla.repository.ReviewRepository;
import com.project.TabernasSevilla.repository.TableRepository;
import com.project.TabernasSevilla.repository.WaiterRepository;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.AdminService;
import com.project.TabernasSevilla.service.BookingService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;


@WebMvcTest(controllers = BookingController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class, includeFilters = {
		@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })

public class BookingControllerTest {
	
	@Mock
	Actor actor;
	
	@Mock
	Booking booking2;
	
	
	@MockBean
	private UserService userService;

	@MockBean
	private ActorService actorService;

	@MockBean
	private AdminService adminService;

	@MockBean
	private DishService dishService;

	@MockBean
	private EstablishmentService establishmentService;

	@MockBean
	private BookingService bookingService;

	@MockBean
	private AuthorityService authService;

	@MockBean
	private ReviewRepository reviewRepository;
	
	@MockBean
	private AbstractActorRepository<Actor> actorRepository;

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

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		Booking b = new Booking();
		b.setActor(new Admin());
		b.setId(1);
		b.setContactPhone("655778899");
		b.setEstablishment(new Establishment());
		b.setNotes("Comida de navidad");
		b.setPlacementDate(Instant.now());
		b.setReservationDate(Instant.now().plus(Duration.ofDays(2)));
		b.setSeating(4);
		this.bookingService.register(b, new Admin());

		Establishment est = new Establishment();
		est.setTitle("prueba");
		est.setAddress("calle ");
		est.setCapacity(10);
		est.setCurrentCapacity(10);
		est.setOpeningHours("24/7");
		est.setScore(2);
		est.setId(1);
		this.establishmentService.save(est);
		given(this.establishmentService.findById(1)).willReturn(est); //importantisimo
		given(this.bookingService.findById(1)).willReturn(Optional.of(b));
		given(booking2.getEstablishment()).willReturn(est);
		given(this.actorService.findById(1)).willReturn(new Admin());

	}

	@WithMockUser(value = "spring")
	@Test
	void testCreateBooking() throws Exception {
		mockMvc.perform(get("/booking/init/{id}", 1)).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testList() throws Exception {
		mockMvc.perform(get("/booking/")).andExpect(status().isOk()).andExpect(view().name("booking/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDelete() throws Exception {
		mockMvc.perform(get("/booking/{id}/delete", 1)).andExpect(status().isOk())
				.andExpect(view().name("booking/deleted"));
	}

	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testSaveBooking() throws Exception {
		
		given(this.actorService.getPrincipal()).willReturn(actor); //he tenido que hacer todo esto porque actor no se puede construir con new Actor()
		mockMvc.perform(post("/booking/save").with(csrf())
//				.param("establishment", "1")
				.param("actor", "mockUser")
				.param("placementDate",
						"2021-02-08T14:56:00Z")
				.param("reservationDate", "2021-02-08T21:56:00Z")
				.param("seating", "2")
				.param("contactPhone", "677889900")
				.param("notes", "Que rico"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/index"));
	}
	
	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testBadSaveBooking() throws Exception {
		//SIEMPRE ME PIDE ESTABLISHMENT.TITLE EN LA VISTA
		given(this.actorService.getPrincipal()).willReturn(actor); //he tenido que hacer todo esto porque actor no se puede construir con new Actor()
		
		mockMvc.perform(post("/booking/save").with(csrf())
				.param("establishment.title", "jeje")
				.param("actor", "mockUser")
				.param("placementDate",
						"2021-02-08T14:56:00Z")
				.param("reservationDate", "")
				.param("seating", "2")
				.param("contactPhone", "677889900")
				.param("notes", "Que rico"))
				.andExpect(status().isOk())
				.andExpect(view().name("booking/edit"));
	}


}

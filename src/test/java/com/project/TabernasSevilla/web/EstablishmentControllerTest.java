package com.project.TabernasSevilla.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.EstablishmentController;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.Seccion;
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
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;


@WebMvcTest(controllers = EstablishmentController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })

public class EstablishmentControllerTest {

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
		
		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0, Seccion.CARNES, true,
				null);

		d.setId(1);
		List<Dish> ls = new ArrayList<Dish>();
		ls.add(d);

		Establishment est = new Establishment();
		est.setId(1);
		est.setTitle("prueba");
		est.setAddress("calle ");
		est.setOpeningHours("24/7");
		est.setScore(2);
		est.setDish(ls);;
		
		given(this.establishmentService.findById(1)).willReturn(est);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testViewLocation() throws Exception {
		mockMvc.perform(get("/location/view?id={id}",1)).andExpect(status().isOk()).andExpect(view().name("establishment/view"));
	}
	
}

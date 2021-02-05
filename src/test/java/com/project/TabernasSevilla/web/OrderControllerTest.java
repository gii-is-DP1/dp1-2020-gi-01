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
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.controller.OrderController;
import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.OrderLog;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.domain.RestaurantTable;
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
import com.project.TabernasSevilla.service.OrderService;

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
@WebMvcTest(controllers = OrderController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class, includeFilters = {
		@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class OrderControllerTest {

	private static final int TEST_DISH_ID = 1;

	// @Autowired
	// private DishController dishController;

	@MockBean
	private UserService userService;

	@MockBean
	private ActorService actorService;

	@MockBean
	private DishService dishService;

	@MockBean
	private OrderService orderService;

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
	void setup() { // inicializar establishment y dish

		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0,
				Seccion.CARNES, true, null);

		d.setId(1);
		System.out.println("%%%%%%%%%%%% la id del plato " + d.getId());
		List<Dish> ls = new ArrayList<Dish>();
		ls.add(d);
		given(this.dishService.findById(TEST_DISH_ID)).willReturn(Optional.of(d));

		Establishment est = new Establishment();
		est.setId(1);
		est.setTitle("prueba");
		est.setAddress("calle ");
		est.setCapacity(10);
		est.setCurrentCapacity(10);
		est.setOpeningHours("24/7");
		est.setScore(2);
		est.setDish(ls);
		given(this.establishmentService.findById(TEST_DISH_ID)).willReturn(est); // importantisimo

		RestaurantOrder order = new RestaurantOrder();
		order.setAddress("Calle Calamar");
		order.setEstablishment(this.establishmentService.findById(1));
		order.setDish(new ArrayList<Dish>());
		order.setActor(new Admin());
		order.setType(RestaurantOrder.DELIVERY);
		order.setStatus(RestaurantOrder.OPEN);
		given(this.orderService.findById(1)).willReturn(Optional.of(order)); // importantisimo

	}

	@WithMockUser(value = "spring")
	@Test
	void testCreate() throws Exception {
		mockMvc.perform(get("/order/est/{id}", 1)).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testListActiveByEstablishment() throws Exception {
		mockMvc.perform(get("/order/est/{id}/list", 1)).andExpect(status().isOk()).andExpect(view().name("order/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testUpdateStatus() throws Exception {
		mockMvc.perform(get("/order/{id}/update", 1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/order/1/view"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testListInactiveByEstablishment() throws Exception {
		mockMvc.perform(get("/order/est/{id}/list/prev", 1)).andExpect(status().isOk())
				.andExpect(view().name("order/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEdit() throws Exception {
		mockMvc.perform(get("/order/")).andExpect(status().isOk()).andExpect(view().name("order/view"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testView() throws Exception {
		mockMvc.perform(get("/order/{id}/view", 1)).andExpect(status().isOk()).andExpect(view().name("order/view"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testAddDish() throws Exception {
		mockMvc.perform(get("/order/{orderid}/add/{dishId}", 1, 1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/order/"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteDish() throws Exception {
		mockMvc.perform(get("/order/{orderid}/remove/{dishId}", 1, 1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/order/"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testListInactive() throws Exception {
		mockMvc.perform(get("/order/closed")).andExpect(status().isOk()).andExpect(view().name("order/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testListActive() throws Exception {
		mockMvc.perform(get("/order/open")).andExpect(status().isOk()).andExpect(view().name("order/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDelete() throws Exception {
		mockMvc.perform(get("/order/{orderId}/delete", 1)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testSave() throws Exception {
		mockMvc.perform(get("/order/{orderId}/save", 1)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCheckout() throws Exception {
		mockMvc.perform(get("/order/checkout")).andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
	@Test
	void testRepeat() throws Exception {
		mockMvc.perform(get("/order/repeat/{orderId}", 1)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/order/"));
	}

}
package com.project.TabernasSevilla.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.domain.Dish;
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
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;

@WebMvcTest(controllers = DishController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class, includeFilters = {
		@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
public class DishControllerTest {

	private static final int TEST_DISH_ID = 1;

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

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		given(this.dishService.findById(TEST_DISH_ID)).willReturn(Optional.of(new Dish())); // importantisimo

	}

	@WithMockUser(value = "spring")
	@Test
	void testDishList() throws Exception {
		mockMvc.perform(get("/dishes")).andExpect(status().isOk()).andExpect(view().name("dishes/list"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowDishInfo() throws Exception {
		mockMvc.perform(get("/dishes/" + TEST_DISH_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("dish"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testUpdateDishForm() throws Exception {
		mockMvc.perform(get("/dishes/{dishId}/edit", TEST_DISH_ID)).andExpect(status().isOk())
				.andExpect(view().name("dishes/updateDishForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteDish() throws Exception {
		mockMvc.perform(get("/dishes/delete/{dishId}", TEST_DISH_ID)).andExpect(status().isOk());
	}

	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void createDishSuccess() throws Exception {
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);

		mockMvc.perform(post("/dishes/save").with(csrf()).param("name", "Patatas fritas")
				.param("description", "Muy ricas")
				.param("picture",
						"https://static.wikia.nocookie.net/fishmans/images/f/f9/Uchunippon_front.png/revision/latest/scale-to-width-down/150?cb=20200116094151")
				.param("price", "30.0").param("seccion", "ENTRANTES").param("allergens", "1")
				.param("isVisible", "true")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/dishes"));
	}

	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void createDishError() throws Exception {
		// Primero debo mockear un user con la autoridad ADMIN, porque la anotacion de
		// arriba no me funciona

		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);

		mockMvc.perform(post("/dishes/save").with(csrf()).param("name", "").param("description", "Muy ricas").param(
				"picture",
				"https://static.wikia.nocookie.net/fishmans/images/f/f9/Uchunippon_front.png/revision/latest/scale-to-width-down/150?cb=20200116094151")
				.param("price", "").param("seccion", "ENTRANTES").param("allergens", "1").param("isVisible", "true"))
				.andExpect(model().attributeDoesNotExist("name")).andExpect(status().isOk())
				.andExpect(view().name("dishes/createDishForm"));
	}
	
	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testSaveComment() throws Exception {
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);

		mockMvc.perform(post("/dishes/savecomment/{dishId}", 1).with(csrf())
				.param("reviewComment.comment", "Muy bueno")
				.param("review.rating", "5")).andExpect(model().hasNoErrors()).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/dishes/1"));
	}
	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testProcessUpdateDishForm() throws Exception {
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);

		mockMvc.perform(post("/dishes/{dishId}/edit", 1).with(csrf()).param("name", "Patatas fritas")
				.param("description", "Muy ricas")
				.param("picture",
						"https://static.wikia.nocookie.net/fishmans/images/f/f9/Uchunippon_front.png/revision/latest/scale-to-width-down/150?cb=20200116094151")
				.param("price", "30.0")).andExpect(model().hasNoErrors()).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/dishes"));
	}
	
}

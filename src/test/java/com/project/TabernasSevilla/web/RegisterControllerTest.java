package com.project.TabernasSevilla.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
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
import com.project.TabernasSevilla.controller.RegisterController;
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

@WebMvcTest(controllers = RegisterController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
public class RegisterControllerTest {
	
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
		
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreateCustomer() throws Exception {
		mockMvc.perform(get("/register/init")).andExpect(status().isOk()).andExpect(view().name("register"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEmployeeRegister() throws Exception {
		mockMvc.perform(get("/register/employees")).andExpect(status().isOk()).andExpect(view().name("admin/employees/key"));
	}


	

	@WithMockUser(value = "spring")
	@Test
	void testCheckKey() throws Exception {
		mockMvc.perform(get("/register/check-key")).andExpect(status().isOk());
	}
	

	

	@ExceptionHandler
	@WithMockUser(value = "spring") 
	@Test
	void testSaveUser() throws Exception{
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);
		
		mockMvc.perform(post("/register/save")
							.with(csrf())
							.param("username", "Patatas fritas")
							.param("password", "1234567") 
							.param("form.name", "El Pepe")
							.param("acceptTerms", "true")
							.param("form.surname", "Cabrales")
							.param("form.email", "elpepecabrales@gmail.com")
							.param("form.phoneNumber", "765132956"))
						.andExpect(status().is3xxRedirection())
						.andExpect(view().name("redirect:/login"));
	}
	
	@ExceptionHandler
	@WithMockUser(value = "spring") 
	@Test
	void testBadSaveUser() throws Exception{
		
		
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);
		
		mockMvc.perform(post("/register/save")
							.with(csrf())
							.param("username", "")
							.param("password", "1234567") 
							.param("form.name", "El Pepe")
							.param("acceptTerms", "true")
							.param("form.surname", "Cabrales")
							.param("form.email", "elpepecabrales@gmail.com")
							.param("form.phoneNumber", "765132956"))
						.andExpect(status().isOk())
						.andExpect(view().name("register"));
	}
	
	
}

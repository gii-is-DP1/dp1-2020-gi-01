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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.controller.TableController;
import com.project.TabernasSevilla.domain.Booking;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@WebAppConfiguration
@WebMvcTest(controllers = TableController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class TableControllerTest {
	
	private static final int TEST_DISH_ID = 1;

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
	void setup() { // inicializar establishment y dish
		
		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0, Seccion.CARNES, true,
				null);

		d.setId(1);
		System.out.println("%%%%%%%%%%%% la id del plato "+d.getId());
		List<Dish> ls = new ArrayList<Dish>();
		ls.add(d);

		Establishment est = new Establishment();
		est.setId(1);
		est.setTitle("prueba");
		est.setAddress("calle ");
		est.setCapacity(10);
		est.setCurrentCapacity(10);
		est.setOpeningHours("24/7");
		est.setScore(2);
		est.setDish(ls);
		establishmentRepository.save(est);
		System.out.println("############ todos los establecimientos: " + establishmentService.findAll());
		
		given(this.dishService.findById(TEST_DISH_ID)).willReturn(Optional.of(new Dish())); //importantisimo
		
	}
	
//	@GetMapping("/establishment/{id}")
//	public String manageTables(@PathVariable("id") int establishmentId, Model model) {
//		Establishment est = this.establishmentService.findById(establishmentId);
//		List<RestaurantTable> tables = this.tableService.findByEstablishment(est);
//		for(RestaurantTable t: tables) {
//			t.getBooking();
//		}
//		Long occupied = this.tableService.getOccupancyAtRestaurant(est);
//		Long freeTables = this.tableService.countFreeTables(est);
//		String estimate = this.tableService.estimateFreeTable(est);
//		List<Booking> bookings = this.bookingService.findUnallocatedByEstablishment(est);
//		model.addAttribute("bookings", bookings);
//		model.addAttribute("estimate", estimate);
//		model.addAttribute("totalTables", tables.size());
//		model.addAttribute("freeTables", freeTables);
//		model.addAttribute("freeTables", freeTables);
//		model.addAttribute("occupied", occupied);
//		model.addAttribute("establishment", est);
//		model.addAttribute("tables", tables);
//		return "table/list";
//	}
//
//	// create table
//	@GetMapping("/establishment/{id}/add")
//	public String addTable(@PathVariable("id") int establishmentId, Model model) {
//		Establishment est = this.establishmentService.findById(establishmentId);
//		this.tableService.quickCreate(est, 1);
//
//		return "redirect:/table/establishment/" + establishmentId ;
//	}
//
//	// delete table
//	@GetMapping("/{tableId}/delete")
//	public String deleteTable(@PathVariable("tableId") int tableId, Model model) {
//		RestaurantTable table = this.tableService.findById(tableId);
//		Establishment est = table.getEstablishment();
//		this.tableService.delete(table);
//
//		return "redirect:/table/establishment/" + est.getId() ;
//	}
//
//	// modify table
//	@GetMapping("/{tableId}/modify")
//	public String modifyTable(@PathVariable("tableId") int tableId, Model model,@RequestParam(required=false) Integer bookingId,@RequestParam(required=false) final Integer num,@RequestParam(required=true) final Integer cap, @RequestParam(required=false) final Integer oc) {
//		RestaurantTable table = this.tableService.findById(tableId);
//		table.setSeating(cap);
//		if(oc !=null) {
//			table.setOccupied(oc);
//		}
//		if(num!=null) {
//			table.setNumber(num);
//		}
//		if(bookingId!=null) {
//			Booking booking = this.bookingService.findById(bookingId).get();
//			table.setBooking(booking);
//		}else {
//			table.setBooking(null);
//		}
//		this.tableService.save(table);
//		Establishment est = table.getEstablishment();
//
//		return "redirect:/table/establishment/" + est.getId() ;
//	}
//	
//	@GetMapping("/{id}/seat")
//	public String seatTable(Model model, @PathVariable("id") int tableId) {
//		RestaurantTable table = this.tableService.findById(tableId);
//		table.setHourSeated(Instant.now());
//		this.tableService.save(table);
//		Establishment est = table.getEstablishment();
//		return "redirect:/table/establishment/" + est.getId() ;
//	}
//	
//	@GetMapping("/{id}/unseat")
//	public String unseatTable(Model model, @PathVariable("id") int tableId) {
//		RestaurantTable table = this.tableService.findById(tableId);
//		table.setHourSeated(null);
//		table.setOccupied(0);
//		this.tableService.save(table);
//		Establishment est = table.getEstablishment();
//		return "redirect:/table/establishment/" + est.getId() ;
//	}
	
	//obtain the list of all dishes
	@WithMockUser(value = "spring")
	@Test
	void httpResponse() throws Exception {
		mockMvc.perform(get("/dishes")).andExpect(status().isOk());
	}
	
	//obtain the information of one dish
	@WithMockUser(value = "spring")
	@Test
	void dishList() throws Exception {
		mockMvc.perform(get("/dishes/"+TEST_DISH_ID)).andExpect(status().isOk()).andExpect(model().attributeExists("dish"));
	}
	
	//create new dish
	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN") 
	@Test
	void createDishSuccess() throws Exception{
		//Primero debo mockear un user con la autoridad ADMIN, porque la anotacion de arriba no me funciona
		
		User mockUser = new User();
		Set<Authority> ls = new HashSet<>();
		ls.add(new Authority("ADMIN"));
		mockUser.setAuthorities(ls);
		mockUser.setUsername("mockito");
		given(this.userService.getPrincipal()).willReturn(mockUser);
		
		System.out.println("=========>"+this.userService.getPrincipal().getUsername());
		System.out.println("=========>"+this.userService.getPrincipal().getAuthorities());
		
		mockMvc.perform(post("/dishes/save")
							.with(csrf())
							.param("name", "Patatas fritas")
							.param("description", "Muy ricas")
							.param("picture", "https://static.wikia.nocookie.net/fishmans/images/f/f9/Uchunippon_front.png/revision/latest/scale-to-width-down/150?cb=20200116094151")
							.param("price", "30.0")
							.param("seccion", "ENTRANTES")
							.param("allergens", "1")
							.param("isVisible", "true")
							.param("save", "Save Dish"))
						.andExpect(status().is3xxRedirection())
						.andExpect(view().name("redirect:/dishes"));
	}
	
	
}

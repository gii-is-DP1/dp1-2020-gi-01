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
@WebMvcTest(controllers = OrderController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class, 
	includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class OrderControllerTest {
	
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
	
//	// create
//		@GetMapping("/est/{id}")
//		public String create(Model model, @PathVariable(value = "id") Integer establishmentId) {
//			RestaurantOrder order = this.orderService.findDraftByPrincipal();
//			Establishment est = this.estService.findById(establishmentId);
//			if (order == null) {
//				order = this.orderService.initialize(est);
//			} else {
//				order.setEstablishment(est);
//			}
//
//			return createRegisterEditModel(order, model);
//		}
//
//		// list active orders in establishment
//		@GetMapping("/est/{id}/list")
//		public String listActiveByEstablishment(Model model, @PathVariable(value = "id") Integer establishmentId) {
//			Establishment est = this.estService.findById(establishmentId);
//			List<RestaurantOrder> orders = this.orderService.findActiveByEstablishment(est);
//			List<String> deliveryStatus = RestaurantOrder.DeliveryStatus;
//			List<String> pickupStatus = RestaurantOrder.PickupStatus;
//			List<String> eatInStatus = RestaurantOrder.EatInStatus;
//			model.addAttribute("deliveryStatus", deliveryStatus);
//			model.addAttribute("pickupStatus", pickupStatus);
//			model.addAttribute("eatInStatus", eatInStatus);
//			model.addAttribute("orders", orders);
//			model.addAttribute("est", est);
//			return "order/list";
//		}
//
//		// update status and/or table
//		@GetMapping("{id}/update")
//		public String updateStatus(Model model, @PathVariable("id") int orderId,
//				@RequestParam(required = false, value = "est") Integer estId,
//				@RequestParam(required = false, value = "sts") String status,
//				@RequestParam(required = false, value = "tbl") Integer tableId) {
//			String res = "redirect:/order/" + orderId + "/view";
//			if (estId != null) {
//				res = "redirect:/order/est/" + estId + "/list";
//			}
//			try {
//				RestaurantOrder order = this.orderService.findById(orderId).get();
//				if (tableId != null) {
//					RestaurantTable table = this.tableService.findById(tableId);
//					order.setTable(table);
//					this.orderService.save(order);
//				}
//				if (status != null && !status.isBlank()) {
//					this.orderService.updateStatus(order, status, this.userService.principalIsEmployee());
//				}
//			} catch (Throwable t) {
//				model.addAttribute("message", t.getMessage());
//			}
//			return res;
//		}
//
//		// list closed/cancelled order for establishment
//		@GetMapping("/est/{id}/list/prev")
//		public String listInactiveByEstablishment(Model model, @PathVariable(value = "id") Integer establishmentId) {
//			Establishment est = this.estService.findById(establishmentId);
//			List<RestaurantOrder> orders = this.orderService.findInactiveByEstablishment(est);
//			model.addAttribute("orders", orders);
//			model.addAttribute("prev", true);
//			model.addAttribute("est", est);
//			return "order/list";
//		}
//
//		// view draft order (principal)
//		@GetMapping("/")
//		public String edit(Model model) {
//			try {
//				RestaurantOrder order = this.orderService.findDraftByPrincipal();
//				return createRegisterEditModel(order, model);
//			} catch (Exception e) {
//				return "order/view";
//			}
//		}
//
//		// view non draft order
//		@GetMapping("/{id}/view")
//		public String view(Model model, @PathVariable("id") int orderId) {
//			RestaurantOrder order = this.orderService.findById(orderId).get();
//			order.getDish();
//			order.getEstablishment();
//			if (order.getStatus().equals(RestaurantOrder.DRAFT)) {
//				Double total = this.orderService.calculateTotal(order);
//				model.addAttribute("total", total);
//			} else if (order.getStatus().equals(RestaurantOrder.CANCELLED)) {
//				OrderCancellation cancel = this.orderCancellationService.findByOrder(order);
//				model.addAttribute("cancel", cancel);
//				List<OrderLog> logs = this.orderLogService.findByOrder(order);
//				model.addAttribute("logs", logs);
//			} else {
//				if(this.userService.principalIsEmployee()) {
//					switch(order.getType()) {
//					case RestaurantOrder.DELIVERY:
//						List<String> deliveryStatus = RestaurantOrder.DeliveryStatus;
//						model.addAttribute("deliveryStatus", deliveryStatus);
//						break;
//					case RestaurantOrder.PICKUP:
//						List<String> pickupStatus = RestaurantOrder.PickupStatus;
//						model.addAttribute("pickupStatus", pickupStatus);
//						break;
//					case RestaurantOrder.EAT_IN:
//						List<String> eatInStatus = RestaurantOrder.EatInStatus;
//						model.addAttribute("eatInStatus", eatInStatus);
//						break;				
//					}
//				}
//				List<OrderLog> logs = this.orderLogService.findByOrder(order);
//				model.addAttribute("logs", logs);
//			}
//
//			model.addAttribute("order", order);
//
//			return "order/view";
//		}
//
//		@GetMapping("/{orderId}/add/{dishId}")
//		public String addDish(Model model, @PathVariable("orderId") int orderId, @PathVariable("dishId") int dishId) {
//			RestaurantOrder order = this.orderService.findById(orderId).get();
//			try {
//				Dish dish = this.dishService.findById(dishId).get();
//				this.orderService.addDish(order, dish);
//				return "redirect:/order/";
//			} catch (Throwable t) {
//				return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
//						t.getMessage());
//			}
//		}
//
//		@GetMapping("/{orderId}/remove/{dishId}")
//		public String removeDish(Model model, @PathVariable("orderId") int orderId, @PathVariable("dishId") int dishId) {
//			RestaurantOrder order = this.orderService.findById(orderId).get();
//			try {
//				Dish dish = this.dishService.findById(dishId).get();
//				this.orderService.removeDish(order, dish);
//				return "redirect:/order/";
//			} catch (Throwable t) {
//				return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
//						t.getMessage());
//			}
//		}
//
//		@GetMapping("/closed")
//		public String listInactive(Model model) {
//			List<RestaurantOrder> orders = this.orderService.findInactiveByPrincipal(this.actorService.getPrincipal());
//			model.addAttribute("orders", orders);
//			model.addAttribute("prev", true);
//			return "order/list";
//		}
//
//		@GetMapping("/open")
//		public String listActive(Model model) {
//			List<RestaurantOrder> orders = this.orderService.findActiveByPrincipal(this.actorService.getPrincipal());
//			model.addAttribute("orders", orders);
//			return "order/list";
//		}
//
//		@GetMapping("/{id}/delete")
//		public String delete(Model model, @PathVariable("id") int orderId) {
//			RestaurantOrder order = this.orderService.findById(orderId).get();
//			Assert.isTrue(
//					order.getStatus().equals(RestaurantOrder.CLOSED) || order.getStatus().equals(RestaurantOrder.CANCELLED),
//					"Cannot delete active order");
//			model.addAttribute("order", order);
//			return "order/view";
//		}
//
//		@GetMapping(value = "/{id}/save")
//		public String save(Model model, @PathVariable("id") int orderId) {
//			RestaurantOrder order = this.orderService.findById(orderId).get();
//			Assert.isTrue(this.orderService.checkOwnership(order, this.actorService.getPrincipal().getId()), "Cannot save this order");
//			this.orderService.contextualSave(order);
//			return "redirect:/order/open";
//		}
//
//		@GetMapping(value = "/checkout")
//		public String checkout(@RequestParam(required = true) Integer id, @RequestParam(required = true) String type,
//				@RequestParam(required = false) String address, Model model) {
//			RestaurantOrder order = this.orderService.findById(id).get();
//
//			order.setType(type);
//			order.getEstablishment();
//			try {
//				if (!address.isBlank()) {
//					order.setAddress(address);
//				} else {
//					Assert.isTrue(type.equals(RestaurantOrder.PICKUP), "Cannot place order without delivery address");
//				}
//				this.orderService.save(order);
//				return "redirect:/order/" + order.getId() + "/view";
//			} catch (final Throwable e) {
//				return createRegisterEditModel(order, order.getEstablishment(), this.dishService.findAll(), model,
//						e.getMessage());
//			}
//
//		}
//		
//		@GetMapping(value = "/repeat/{id}")
//		public String repeat(Model model, @PathVariable("id") int orderId) {
//			
//			//borro los platos que hay en el order draft y le meto los platos del order que quiero repetir
//			Optional<RestaurantOrder> pastOrder = this.orderService.findById(orderId);
//			List<Dish> ls = pastOrder.get().getDish();
//			Integer establishmentId = pastOrder.get().getEstablishment().getId();
//			
//			RestaurantOrder order = this.orderService.findDraftByPrincipal();
//			Establishment est = this.estService.findById(establishmentId);
//			if (order == null) { //si no hay order draft, se crea uno
//				order = this.orderService.initialize(est);
//			} else {
//				order.setEstablishment(est);
//			}
//			//aqui podria borrar antes los platos del draft, pero a lo mejor el cliente quiere conservarlos
//			for(Dish dish: ls) {
//				this.orderService.addDish(order, dish);
//			}
//			
//			return "redirect:/order/";
//		}
	
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

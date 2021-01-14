package com.project.TabernasSevilla.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.project.TabernasSevilla.configuration.SecurityConfiguration;
import com.project.TabernasSevilla.controller.DishController;
import com.project.TabernasSevilla.domain.Allergen;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.repository.*;
import com.project.TabernasSevilla.security.AuthorityRepository;
import com.project.TabernasSevilla.security.UserService;
import com.project.TabernasSevilla.service.ActorService;
import com.project.TabernasSevilla.service.CustomerService;
import com.project.TabernasSevilla.service.DishService;
import com.project.TabernasSevilla.service.EstablishmentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

//@RunWith(SpringRunner.class)
//@WebAppConfiguration
@WebMvcTest(controllers=DishController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class, includeFilters = { @ComponentScan.Filter(Service.class)} )
//@MockBean(JpaMetamodelMappingContext.class) //para que evite buscar la database
public class DishControllerTest {
	
	@Autowired
	private DishController dishController;
	
    @MockBean
	private UserService userService;
     
    @MockBean
    private ActorService actorService; 
     
    @MockBean
    private DishService dishService; 
    
    @MockBean
    private EstablishmentService establishmentService;
    
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
    private PromotionRepository prom;
    
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
    
    //POR ALGUN MOTIVO HE TENIDO QUE CREAR TODOS ESTOS MOCKBEANS PARA QUE FUNCIONE EL TEST SIMPLE DE HTTPRESPONSE 
    
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() { //inicializar establishment y dish
		

		Dish d = new Dish("Mi plato", "Mi descripción",
				"https://international-experience.es/wp-content/uploads/2019/08/comidas-mundo.jpg", 20.0, 4.0,
				null);
		
		d.setId(1);
		List<Dish> ls = new ArrayList<Dish>();
		ls.add(d);
		
		Establishment est = new Establishment();
		est.setId(1);
		est.setTitle("prueba");
		est.setAddress("calle my deaths");
		est.setCapacity(10);
		est.setCurrentCapacity(10);
		est.setOpeningHours("24/7");
		est.setScore(2);
		est.setDish(ls);
		establishmentRepository.save(est);
		System.out.println("############ todos los establecimientos uwu: "+ establishmentService.findAll());
	}
	
	@WithMockUser(value = "spring")
    @Test
    void httpResponse() throws Exception {
		mockMvc.perform(get("/dishes/list")).andExpect(status().isOk());
	}

}

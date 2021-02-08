package com.project.TabernasSevilla.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import com.project.TabernasSevilla.controller.ActorController;
import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.forms.ActorForm;
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

@WebMvcTest(controllers = ActorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class, includeFilters = {
		@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
public class ActorControllerTest {

	@MockBean
	private UserService userService;
	
	@Mock
	Actor actor;

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
		given(this.actorService.getPrincipal()).willReturn(actor);
		given(this.actorService.formatForm(actor)).willReturn(new ActorForm());
		given(this.actorService.save(actor)).willReturn(actor);		

	}

	@WithMockUser(value = "spring")
	@Test
	void testProfile() throws Exception {
		
		mockMvc.perform(get("/actor/profile")).andExpect(status().isOk()).andExpect(view().name("actor/view"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testEdit() throws Exception {

		mockMvc.perform(get("/actor/edit")).andExpect(status().isOk()).andExpect(view().name("actor/edit"));
	}

	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testSave() throws Exception {	
		mockMvc.perform(post("/actor/save").with(csrf())
				.param("name", "Pepe")
				.param("id", "1")
				.param("surname", "Perez")
				.param("avatar","")
				.param("email", "a@us.es")
				.param("phoneNumber", "677889900"))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:profile"));
	}

	@ExceptionHandler
	@WithMockUser(value = "spring", roles = "ADMIN")
	@Test
	void testBadSave() throws Exception { //bad porque la foto no es válida
		mockMvc.perform(post("/actor/save").with(csrf())
				.param("name", "Pepe")
				.param("surname", "Perez")
				.param("avatar","data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhMTExMVFhUXGRoWGBgYGBgXGBgYGRYXGBgXIBoYHSggGBolIBoYITEiJSkrLi4uHR8zODMtNygtLisBCgoKDg0OGxAQGy0lHyUtLS0vKy0tLS0wLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAOEA4QMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAEBQMGAAIHAQj/xABHEAABAgMGAggDBQQHCAMAAAABAhEAAyEEBRIxQVFhcQYTIjKBkaGxwdHwFEJSYuEHI5LxFRYzcoKisiQ0Q1NUk8LSF3PT/8QAGgEAAgMBAQAAAAAAAAAAAAAAAwQBAgUABv/EACwRAAICAQQABQQCAgMAAAAAAAABAgMRBBIhMRMiQVFxFDJhgQUzQrEjJPD/2gAMAwEAAhEDEQA/AOgomGJ5axA4TFU6Z9MxYwES0hc1WQL4UilS2ZLin0XZyilyJ1KUnhF0UutctornSPpClIKEqbcxy+8emtrWkkzCnMYk9nIAnANdnL95ObxWbLPHamLPWLJB7QCtfxKcjw2EA8dL0Gvpm+Gzo0u3pWThq2fONU3ih2fQqyNQCxIOvhHPLde01fZKmCiKJ7NBRy2cMU9IP3fU4uql4cLhGNQSEKAADguVEnMAExP1cvYn6Ve5e5KwpIUmoOR3jdoo1zXnZ5ZxInz1MoHAtDHIBRGFSkkHLCSn7tXYi8ptKFKKUuFABRSoEHCp8Kg+YLEUyIIg9eoU+GAspceUZhjxokjxoOBNMMegRu0bNHHGjRHOmBIdRYQHel8JlCnaU5DOwDM7+cVy8r3VNyBDhqFw1dGDEQOViRaMWyW+76xumWVBOW2Ia+sJgkuzOfjvGT09kU+f8oms6AxLs3t84UlNyYdRSNRIcmn01TwiEqYkD9Ilmz3ozJ235xkqW7uQkF961FA38opyWZohK1lhXSh9OEGSLsfEVrSkJLU7SidgDzzyhyi8ZEuUqWiWgkUolwos2Mlzi84UycTqcgE51+WcXeIlU2zU9W7S0mmZJc78nochEiZDganZ9Ph4R6tKQkuc9TTTQeMQqmE0HDRvaB5bLYwep37LsxfQv5aeseLSDmaZnjwAjUI1hjYrmmzWYMncikWUWyG8CcjanjE9jsK5iglAc8NBuToIud3dG5Uuq2mK490f4dfGG6EBIZIAGwAA8hBo0+4N2exX7u6LS0gGb21bAkJHiGKvSG8mwy0hgkNnWvDXgAPCC48g6il0DbbIeoT+FP8ACPlHsSxkSQWPpFe4kJLJcsWHFi3E/ofHhd53omdPmLmKJFBsSHdYDd3vFLjSkPOmHTIrx4QcRCgFUZIVoOQbmQOEc6KyzaO/pGW25cs0opVrCC7Za8anGRoBskZeecQiboIyzyio0EGyrpW7EZxZRb6RRzS7YGVVrGil66+0GW67yjPWFyg0Q1glPK4GF3W1Et8UrE+uRHLSLjcPSKTMQmXaCTgP7uYcIWh8w4zS4yI24Rz8mkRkxBOcHZ7NNDlpmMUUCwBwq7p7NCDooZ8wYMFY5n0avabJSOsfqK4Se8C9cA+8CcxlnUGC7V0mnzVMhRlJ0Slio81N6D1huF+2PmFpU7n5TomGAr4tJly1KDvwbgPjG9zz1TJMtau8U14kUJ9IQdKbclRCEl2d/aDyl5cgFHnAgmkqU6jnU8SS8aJOw5RslBUT4fpGTCE0FT9eQhNsYI5nE/X19GNlg0Gm3xMYU0dRc6ULcWieTZCoOXD/AFl9aRBJtd05KF4lpKgyqClTl4R5hMxRNXUT4axIlKQkPnR3oz5hsyeUYMOQNNsyfAZfW0dlnYMkpDKJqxyDAnRn+qPGYyNAKbhszvnn6RiZbAlmD0Lg+2cb2SxrmqwpBUT6fIRyWTm8EGF+PGDLLYlLKQkZ0FM6Q6FxCWEYzUntHEAEpDPRq6DPPSLFZ7MlJxBATTCkahOZfiXg0a8g3MW3VcSEMpaXVxY19gNtd9g5MaWi0JR3yE84Wz7/AJSThJJLUyDvwUQWGp+NILuhD1KqE5dIaNHrQik9KJJUUnElq9oMANC5pXbPhDuzzkrSFJLg/XhFo2Rl0yJVyj2jZoxo2aMaLFDWPY9aMjjip9MehzzZhQlIGSEpU7BICdfr1iso6GTMVaJc15FnjtNqsjgkZqVXTLP4wPJutkuotsKasc4UUIFpXW5wjmdhupEglKkvxbWDly5aSHTTYUh9fNnQMQJ4iEl6YKAGrO58tYdrjHHRj6m6e/GRfedhRNTROEgPoa5V4VikWyXhLEfKOgzV9WMqkVfWkU695Dk7wHUULGUOfx+rlnbLoQlonsskPiVkMhuduUCmD1lkgcKfGE4L1NacvReoSJypisSiScg+gyA/SC5CGrtTx1hbZ1MH4w0sd4IRNk4tCCp6gJ1PEs58OMUay8hlJRjhF3Ve8qTISlKgVBLACtW1yZ6xVQCpTmr1P15RNbrIZcxSM05g8HoBmeHhEc9ZHZHePtudhw8YPKe5Cyhhmk+eE0Tn9ZwPISXfM58uJ942lynNK8fjBvVBKQDlrucqQMsRS7O9S5Gm59IlmzlDspYHhVvH6yjxc8tt8fkI8RJyxFn0GeW2viw4xJBHKs5Vr5Zb56wXLlJSKBzq/wBV8Ke8aqnPQBhw1/SGF32VSymlFFICmFHNC1HFDwoYlLJzZ7YLpUshUzEmWfvBJUGHtziy9fIs0sBDElqP2i+qmqIIXZ5+Gq0FshhI5ZHOB0XcB1ai5Lgl9mcU5gCDxjjoE3kklyQT1swOqhAIybusDlVy+eUJL/6XSZPZxlS3IKUEEpIORNQNt+UF38qZNT1clQGIF1ZMHAxYjQDP1bhTbZLs1ncICZqvxKKWcepfOpyA3gNt3+MRqqj/ACkbDpHMnHGmzg0OLUEHmDlnT0ga0qX2QZUsMjMBJPaLgtm4Jz84Fn3+s06wimE9lJHuYj/pVasQ7BcYcsJO1aknxhXA0mkafbk90pWghmY0CgarL5Hwh9dXSBUn/iAodjiUVBRIzIyA88iNortus6iXAYnSu3MwLLQQQFE4ebtEr8EPPTOlWHpYmmNmU5GAEkJDCte1rltrFqss9MxIWggpORGscQRMwKzKk6NSmo3D8CIc9H+kk+VMSmUlBSogFCiQCWAHa+6TSu8MV3tcS6FraIvmPDOuNGRVf64L/wCgtn/bMZDPiwFPCl7HRJNplzFEuDu+Xr9ZQo6QX4kIUlBYjPccWim3DeE1ctRQDm5biB8jCu+Z04KxAEOK6tp5UjlCKeTOepnLypYYyvC14lJmFXZyr92F1tvizpZl4lcASPOKpbcZIxKUX0/SGMixKSEgyjiUHT2XfKgZ61fkDHS1Ki8dER0Cl5m2/gsyb3k2lCUlkrQGGmL5xX7fIYl4HnS5gOFUtlbUeGkmyqMtl0OhOcFhNTQKUPBlnP6KZeEllQWbLiAL5CkTXzZSEknSBELUwANCH9IUcVGTTNaNjnBSTNjMAqSzaQCuaSrFrn+keTC5MeIOVHrlvwgLYyk+2dAttpK7LZppYrwJS+p7wKuJOFKvEwtRLoRqe8T7RIcIQZSS6ZUxSU7mWo9Yg/5lDwEbrSxP1t+sdEmfZMEgJYU0jWYHrR8kj0fjrEWacLVJ9I2A2Ozq+AiShvLlgGvaVm+gP1vESxoHc1JPtEgDqCQKfHzpDy5riK5n7wEJZxUOaa+lM4vGLZDZnR3o+ZgExYAQ4YF+0PvUHk7jkYtlokAdUBQCYnUvkrX5xNY0tLRwSM+USKS/mD5QwopIC5ZPCIS3leCFSv3KxM0VgLkJH/ko4Uh9VDOHM6ViQtO6VDzSRFA6RSMGFUs4VEaZEbEZEcIDfa44S9RrTUKxOXsL+md7AtLCyAwCwjIrBqz5IGVA5ILmKjLTKOePnQ/XrDAySs/ujhmfgfsqP5Se6rYE8iMoWTJ6+6XDFilsLNRiG020rCqQw5pvARLs8ojF2jzIA8hWJJcuV90F98X6D4QNKWnJQHhnDuxXT1jFNNfrjyiVFyeERKcILLB51jWgOjrHP4kuCG0YQEtau6vEC9QfXkY6BdFlBTgUc8nqx8dYy2XDLJZbEsatn4wdaWQlL+RgmUMJw/3Tp+saLkMsAGhy+t4sEy5A6htDfov0fRMnJxjEEuwOR8dxpES001yFhrqp8Jlc/pOd/wA61f8AcPyjI65/VBG/+r/2jIpsfuF8WPsZdUiXKsEpSK4kFTkMS5oTFJve0O/On19aRculCjKlypBAGCWhJbJwGPtFCty2J8+Ff1h+pccnndS27EkhEZYWVkvokNRgCHixWO8rV1eALoMiwceID+sC2aWGxAVzO8eypig5DpD13HGKzohLmSyXV8/tjwHSZIl1WcSjuanxgO8b1I7IA84W2m1Fz8YHRKJzy9YLuSWEBjRzum8kNvmFaVk6JMDKl4ZadykHzDt6wZeBCZawNo0VKdIOfZS3kIXksz/Q/XJKH4yV85wwuCUFTalmBUM6kEFqbhx4wAc4ZdHLP1loRLoynFeRI8aQmzUj2i021CRbJstKWQZaSir909lT5ZU5NEKA7PWrPueAOgjXpRMa30/AnxcA+VTG8vu7M/8AL2i0Oitn3HigwPqd+HKMkJxEfWXt+sezUEsnOoFNzkkDfLzhrZruKACujqAPBjUMA+euUXSBtht2WYJllZSCXqH2VqQMq08c4uNnkgBPZALZOSz1MC2KQOpfQOXAwlQGIEto4enrDJIybaGIrAJmjRgTEjRshMWIwRKDB9qxzG2W0TJkxLVSS4eoBUWPPcaR0i+ZmCUo1qUpo2aiBrHDFXlgtS5oHZK1ApFeyVGlc205CFbnlpDdEnCLaGc+xguryOr6fGCp0qVaZBUUjrw4KhTERQKPxhxZbMidLJRVJDk7DmdorsnDKICVYqkE6cn1/WOhHDw/UDdYpLdHtMEui7yosrfbNsxF7sVlSAlUsM2Y2hHZiHcUyc7bGLjclmJqQytdlcRDtdSrjkydTqZ3yS9AW0yslpo315wDes80WNIuZuxASSSANQdIrdvsKA5lnEnZ4JCaYtOpw5Yp+1hwpgzVia5bdhmnCGq/OIbRKwBgHB/ymAruCsai3Hy/lFsZ4JUnFZOj/wBZlfh9oyKr9oMZAfBQx9VYadNel0pc5SAjIJ7Rd1Ud2bdx4RSbVeHWulKVdpqnIRfOn6LKqVZpGJCVpGMKPeZXeqzgE+w2iqS5tnDDGAQNiH8xAI56zwHsaTyo5YRdyCE4M3FDGWxeHsjSkeSLcj7qqxBaFPrBxLLzygSZvSB1THoHiaekmgjQKSnnEB4kFsDS1vsaegjSWT1aANh7CNLxnuhQTU68ngmxy3SnYNFFzJ/AfqCb9yvTkMoxZegdnSucxBJSMYYGhSQ1dP0j2+7AJxlqlCqmRt2tj6V2j3ofM6qa5OFTFJCnYu2EHYOamE7YuJp6eSnjBr0qm4bSlZq8sf5VM3+X1hgksCU1dmPBhWEHSoNNCWYAOnXsqZQrzfxeGPRi0dZL6o95OXIu3kfYRWBe3tln6P2daZstkjHhC5bvrR2arh84e37YVYkulJJPfCWKiVsz4jphzyptBKbMRORMBPaWmW2ZSjAkoyH5TnuIY37Z8clQ1zHP6+MNKPArnkmsfcD/AD84nSlo1lkEUaJUiCFDAmJEpj1KY3wxVsskVHp1aABKS9f3kwglhhSgoz5rTHD5lT9e+sdf/aVOI0fDLU1aJUQqvEkBhtnpHIUiohWb8w1FeVIZ3TbiiXMlKWRLmgh9Eq5bRtZZKZdOsQtSilggkgAO5JIFdG5xFZJGJJG9YjsFneYQNI6uW6WCNRT4de70Z0S5ruHezBES3rfapQEpJwtmrXkIg6P23CAlWQ9IY3xdCJqcYqcx8qGNObaPOwgm8sq1qt+IUWUndU1KSa7KIJ8IAkX1OQqi3PFi41qIZzrBZpkvqjMFnIUFKCgA5D0CyKipo45QovCyS+uHU9pIYFQIYnXKElqJuWGsGn9PWo5XI5l3qpbLI57Hi3CJpNsIxNuw4ZkmJ7qu5Lg4XyJHPxoIsiLkl/ZZ80p/s8R5hnb0h3ftXJnKG+WIoq/21O6oyEH9J2r/AJMry/WMgP1cBj6KwfWiWlEufOKUzJipktCcYxYUdWtRZ8gST5Qnl2sqoQkDYAAekMbFhUtAmdwlieX8/eCZ06SlTBI5iBV4ayEv3J4Fq0SwHID+UDKmiJ7xCFZH4QpmzgkNBmxaEMhc2cAKQttMzbONFTScvOPO7zgcp5GoV7TyYGQrdq+cNxaWCRll6QsXJPVKJ4P4qENUpSPzGL1dkXNYXyH3Uh1SlOwxpLkOzFoc9N7iEmamfLBEuYClYowOeQ7oNa7kZRXpU6gOTFxz+h6RYk34JqJyJk2sxOEOCUhTEDKgyfwjrq9yJ093hyyyg9IZqV4FJdmauYJqRyd24GIej1p6u0IJyJAPKhj28peFRBDF6jYgsR4EGACTicGunwjPjwa8+eUfRstIUQt6EuOA6tm5axl4N1SycgknJ8q+cDdHpuKzyVboST5QfMQFAg5GHl0Ivs1ll0gjYGCUJgeW7B9oIkxLOJ0piUIjVAiSsCZeJyj9rKx2q1okM7MWIf8AN2VDlzjn92yMT829otH7RLWVzB2gUlayAnIBKsIPlrrplFSslsCULQxxKNDs9ITnlrg0KnGLW72C7qBIJAoCz+vtEMua08lJ2/lFi6HFHUKSaqxlR8gG8orNvGG0TMOQUW5QPT2f88ljorqXKVCiy12O0ghlUMPLBeJl0V3TFTsawtObKglNqUKHKNvdlHmtuJFotFhkzziKK8yNX0jQ3NJQmgr4AQmk24pqkvEk+2TF90Viu1BHJrgZWRQamg9jxiy3bL+02efZgsyzMCU4gMtCdIpN1W5IBSogLBZstYs12zhKs65qFOoqSltRq9I6WJLBFacJZIv/AIvtX/Vzf4Vf/pGQx/rZO3MZAfBf4G/qI/kpcoJ/duAQ5J8SkeVIhvDo8tHdmhioAAglnD57QcuW4Bfb1A96wVPlqKAnFmAWZ3IFAOLQgptPhj0q4yXKEV4XEZa1IM0qwlnSAMXKpoRCW8LAELDFwQ7bbiue8WibNK1FZzNeZYAfCFl8WclGMBwir/lJw+9fODqWWClWlHyiYIiBiVQQo0iOQDnBksisXhZJ5soiXlmU+4ghRKQdNPOI7RMJCR+ZNORf4RHNnh6gqWS4TtkK/hpF3NR+To1ua565HV12tEsglIUE1OKg4Ods4CnXsiuFJJNcIyFXYlq+nz1st0Y+1NWHzCdA8NZdjACQ6WSGDDck184IqrJ8t4QB3U1ZS5Yj6SJnBKVTEAJmMsKDmqu07nU1fdjs8JZDUJycE8tY63fdjs9psypCUkFIBlrCsSSQXw1AZnIFKA13jl953auQsIUxBSFpUO6tKhQj24EQnbXsZo6a9WLs7v0emoXZ5RlsU4AxDbcKPDBo4l0K6TrscwJVWQsjGDXC9MYb1GoG8dqslqRNQFy1BSTUKBcGCwmmjpwaZu0epLRsBHuGCFAmUp42tU3BLWoEOEkhywoKcoGlqYwF0st4RZJpFFFkpo7rUoBIAo5dvjArOEEr5eDifStjMTh7gQAkgEBnUAw0ACRTmdYrtnQ6m+sjFg6TTv30xkgAKVk7UYNWtCNd8hlFekLwnFsQfWFEOzxlFl6MHCVJ1OFQ5ZQsvKR/tE0blx4h/nDG51BNpQBkZZHqSPRoFtUj/aZidj71+MCoX/Yf5RXUSxR8MGsc7CrCaQ4EwHvecAWuxFq0OhjSy2gp7Ko1IvbwzGnFTW5djuzoYw6s94SgGcJ3hFJVRxEky2IA7chCm1wh/wBYJKTS4AQgpPEjLyRKmKfGlJ/EyvcCLt0ZsUv7KJKMC1zFDGoOVP8AmxAMAKsKaxz5NqsxUCuQcwWGMA8CBHYOj92S0/7QiV1QWgJSh1GlSVsoliXbkOMAUm3lof8ACio7Ysk/q1I/Ev0jIatHkXyydkTk4IDprma8iQX9I3XagyVVcBvEo/RoHC3BLsQSPUmNJzNR8/SvwjNXY8+iJStuUSyFiozCuyR+Uhm94GSKO7fKJbNny+ngqBsWyrmmGYqUlJVhJ/hAxYn/ALtYFU3ADfL+Zgq+L5KsSUA4WALUCnfDi3yJY0YQmmIUpsRfhoPCDKcmsIUdUIvMn+jJ0/EQlDULg6k1HhQ5VhzdN3tVq6kwDdNk/euRQJ9SWi2WKxqXQUH1rpDWmqS80hTWXviECOTZNVEARMerG5g1ViwjMe8LZ0kaqh6LT6MiWc4YXItCAaFSYVXrZpYWkzgVWYmpTVUkqNVo2BNSnU+soSneCZWFQKSXBox22gdtaksB6LnTLKKh0luRVmWGIXKWMUqanurSeO42hr+z7pN9jmFM0kyF95q4FU7fLfwiazWpNnKrHagV2OaXG8ok0Wg6Mcx47vV72u1UmatD4gkllD7yc0q8QR6xkTi4SPSVWRsifR8ohQCkkEEOCC4INQXjdooH7Ib962SqyrV25VUPmZZ/9TTkRHQiIKpZByjhmmGKr+0Oe0lCAQ2LGqtcCBi8AVYE/wCIDWLZHNOnFq6y0CUSMOJKDkwQHXNUXGWEDenEkRS18BaI+bJRL/SEhKaYsKcRGRWrtqIOzKl8PcolHSGN62grWVKzUSs81nEfRh4QtXAYjFo8u9JaRNLg40ywasQxDZNtrBFrS9rmkfihPItpCZaGDIXjermoO8MbHPUZqlgO5JiaYPxdwtqZ5qaHMw0AMK7RJG0NZttSwxoUktmzjzEDz5BwBbHAqiVaFw4HAsCa7GH5OPWTIipL0BbvEzEyBi4awci+paQQsDFsaEeEeXesIcuylOBuzdo+Acx0n9lkyRNsK3TLmKE5alBSQopxBOEdoZMIq5bcIPXBWNtiH9n4FqVMBlhUtJClKIo4ySDv8I6StTxu4AwpASBkAAAPARq0Ucs9jMYKPRrGR7hjI4tg5KpIwrapBJ8MqfWkBWokMnx5afXOD5TFVBXlwPxaFk1yavvXWEI+45IzD8vD6941tNmWtBEtwrbIEEsQ/KNkGGV1pdQEXKYEF7XMbP1cp3SE9YT+JaqKU3gEjlxge7rEuYpkjxOQ4mLN0qT1s/ACyUBKVF2ADOTsYLuq45k5KUSj1Mr70xSWJToQDmonujYFWTYmN21JR7E9m9uUnwAINnkBlEKVxPPIDkY9X0hlH7hAz7qqGlKJctU0bKLnZei1nkgshJ4mqjxJb2gC85KRRCcPAAPBI6be/NJ5F7Nb4S8sFgrQvSUvJn4Mmpq7M5zHrsY3XKQugIB89+MR267CvvJ84XLu2bL/ALNQ5KJIi0tHZDmuRFf8hRbxYsEy7LVgX139RGiUERoi8CFtNSRoGLJLelfnBcySCAygzZ6u5+Dbx0NXKD22l7dHGa3VMgtlm66WZZzzSeMV2WFTEKlnvyEqUN+rHfT4Zjx3iwoxoUxJcQBf0tUuai0y6H72x5jUEOCNYvfFSjuQLSTdc/DkJLBeUyRNROlqZaCCDvuDwIoRH0dd9sTOlS5qe6tIUPEAtHzdbbPgUKUUAtPFKqjyqDxBjqP7JukvWJNjWRilh5Z1KHqniUv5coSg8PBrTWVkvt52oSpa1nMBhl3jQZxyS/3K1KURR5YKQ7UC5pD1ACBg3JJqIvvTi1hCEEpKgklTA0KmZAIyVUux1AMcivq34iCCC6WBZjgKivEXJOJRJNck4d4izsJSsLIqnrxEnUmuwBrApMSrU5rrm0FzbrUjCVMQpylSS4LGtRR46Ec8FLZkFnlaxYbulDVxxGUQXXYXPDXlDeRJwuU1G20PQr2rJlXW7ngmnCgZiISWmQkOzjg9PKGq5w5QJa0gmLyimuRauTjLgDugjrCVFyMg1dGbmY7n0Xu5MuziZhAmzz1k1Tk4lOQ9eEcluSxgqExQAqyRyzjt9nDSZQ/KPWsJeDtlv9zRrnueDVo9wx6I9ichjzDGRs0ZEZJwcgsI7SnNAoeIftDyPrAtoSAojNg2Wv8AODLMlsRajh+RevmRAyEFSi+b/qfeFkNM0lpNAREyLeiQcSmOrfygO87R1boDuQ76NkfF6QJckoTZqUhOJVACaoRqVEfeUBkMnDnaLRTk8IFOcYrcy33Dcq7WtM6clpbhYQQxXsVflypwPOL4LGAzk+FIGuxOFCUiiRvmfmYPtGVKe8NqO3gRb38gNtWhALnw/nFVvO3pc0J8a+kH3hbUgnsvzzhBa7YpRomkO01+5j6u7PlRDNtyTm4gKaEq7qvWPZ61k7QumJU7tDL4Fq4JvOTLRZCoEKrzhekrknN0HxKeW44QYuarcwHOD51he6uNi5NLTWzrffAyssxKwRQ6pamddmO8ErsvWS1IIejiK9d04IV1au6SSNh6cPeHtmmkKHadOWlKaxm1zdUtkujRvpVsVOPaKxe8791LkqSypSlBKt0Kq3N416MXp9ltKJqgcIcFtAoM/Fs2hx0wsACBMGf19ecU8riLI7ZcBtPYp18l56UX0bRMxKcyU1TmOsVoOKakE5MTq0Vv7JNmErILqLuYbdE7kNpAUV0QcLO5DZDgNovaujsqWnTE3OCVVRk8yZTUaqVaxBHM0Xdh7wjoV29EpEuyrNomFBLFklwlWxSVFKjpkNawomWYdagaY0u2vaybcw4toTMtIlkvgMyYdjMUDr+WiRy4xbVVvKhX8sHpbN0XZZ8fJW+pMtRI7oOEhlBSSPxJVkFMSKmDJch+0mHNhVPtX2mSnqz1asLqCklgpwkqSGfNm+MDzbvmSjhXLMtQJSQ4UlZFcSWJZxXCeLZMLwuUZeHJ/sDZRlb4oV2hAwkqELTKyIiy2uzdYijUz3hZarExGEUaGXETUkuUG2mX1U2RLGiUE81AEx11PdSNkpHoI5paLCqZNsk0ZTESh/iSTLUPMR06aliYUsfRo0Ls0jIyPYEMHrxkeRkdgnJ85XxfZcy0FgCfEsxLxZ+gTzZKlzC+FRBUrZgc/GOf2teJSjk5KvMx1a7pCLLdMlqzJ6RM2LKqdMsk+cLvCQfOWVW9FqUVYRQqwpDkstVQOLJxGuxi3dHLIizy0E1UrPUuczEvR26QZUtcwAlTzmNcxhQfLGrksQQZXVKxKZqt6Vh7S1pRy+2Yv8hc3NRXS/2PLCtSlAksIdziMCuUVWfaiAlQLAt843/pFakEPF5Qb5KVaiME0wK8FpBoIS2q0tBFpLO6nMK7RaU7iHILCMmWZz6BLRbCX0gFU5R1g5a0naAp0vVMTIbqUVxg9lznoqMVITnpAxBfKPZilfD4QKUsB9vswe3pw5d6DbDPSpALnHq1W/EDTKvLKAJ0svlAqZqkEpDDHQ8CaP6wjqIbluNLSTx5Q297UZierScQTUn2aK4ltYtkiUiUFsMwoAGtWcnnp4CKosfH3gHccjMFtk0lwPuhN5Kk2kAHszOyedSk+dPGLvb7YqpKo5fds4ImoUrIHy4+EXeYlWineHtHjDM3+R+5extMtLKD58NH156Dau8ObrUk61pXM1WkHxrCGVZjmRBd0ntqw6M6jwdTD+GDTWIP3YKL3Tj7LpDPofLmJNqwqAechIVmHPWBVWqACI8vq3KVOSUJUtZDhPeScJbtJNCA5r84OlXtLRKTKlJomqjspVBzJJc+EObvXLlKAzK0oIOxOaQToAH84zbfJKUsGrXFTqXyJrFeIU5VKwgKWgskh8CmKg7OMnoGfJiIKvC5xMldbZyCoB1J3TrTQj1j23y0pW6UKopSwUHCSogPv2qAEMxABqRGXfZsSuss82ozZnD6KTlDVU2klJ4f+zOvistxWV6/gh6NJ6+TNsqyAvtLkElqqSykj0UP8Ub/ALOOm4mD7FbVYLRKdCVzCBjwnDgJUf7QZcecC3vZVypgWpJSCQXQaYtSPwnVvIxXv2m3WAqTamSeuSCohmUoADFxfXYp0xCK3x53IPpZ8bWjtikRrHJ+gfTLqD1c0q6pmAFcDN2g+XEDnHWELCgFJIKSAQRkQcjAcjaMjI9jI47g+VFB2ABfLnHSrFLmWg2azswky5aZgScsCQWJOhVUt+I84pNyoxTMZDiUMQpTFkkeftHWLjsPUS6jtzDiXu7UT4D1eB1w3yx6Eai7woZ9WM59mUkY0kPr7NyYAARX7wtwWtIUk9k19Ke8WiwpK0qQtgCPLY+EU62SFS56cTFIWAogv2dT5OY0q33kwrovhrpje+5faAPZQOPl9c4W2m8GomJ+mJPWJDuAkZZE7woRLSASpzqeEXhjamwVsXvaRBaVuS5JgCc0ZbLxlOwUlJBILmrgOxAy25wAm2oWsAKxUc4UrLUdqDwjpXQXqHq01nswsqDQKqYRBSrbKTLSrEKkgp1SBqflEKlyjUrCBTvOkF9nFeO0Vd0H6hY0zXcWeoUTUQRhoFARFZlAFwQRk4r7QyssnEFAbPHPDjlMFPKlhoQTpxfKIZksl3eoannDG3kAkM5HDIfRhfPmnDtFGuBmtvhojl2lwKcPKn1zhLaEso84JSvCojSIbYaxnuOODUi+SBKXIAzNIu0iYWAJqAB5RSXi5IViQlX3mD82hrSvlieuWVHIcq2gBjEItBKVYKPR/rxgBUtzUxHNVh18oZbFIxXp2NbrnYVEbpIPh2qeIi4zLSkzAT9wlKeZGAf6zFDsEwqNdlNzwmHl5TilTDSZiI5LPyhW+O94HqZ7a+fcOvK9ZmNUtCUlyK5vT05w0uy7LUkmbjSkq7WJK8YJAYDApLDIYik1bjFbvO1lE44dgfQRb516PKlVIKggJH5mcZUYn0jrv64AqP7J5LBddvlWhJkzWx5LQQUvQFwCS+hoS0V7pXdBTKVZWxS1HrZBNWWO9L4ljQa05QrtuOecUpH75GoDlUsgqSQ57K0lq01rVoeyLzK5X2e2OlZGILLDCXox1Y0fwNXikZ7JbZdBZQ3x3R7OVTbIZagU0Qtwkk0SsVVLJ9U6kEagx0noFeSpaUyZhJQturJphOTMdCWy1PEkVa/rLhM1E0gJmVxioSsOUTg3FwW0UqArhtCzLSyaN2SDUENk7OWcR1kNj4Oqs3pN99HccJ2Mexzj+tds/Cvy/SMgeWG3Ip/QmwgqlBmf98tX5Q4SPreOhWi2AUSPOkV/oRY3xqdk91w1Eo7LDxr4mGF+0Jwmg0hjTwWOfUzddZJyeOlwD2i+yk+5EJr6tYUMaaEbZwBbLTpACrW1B5bwzJxiK1wlLGRxfXSiX1SJakkTxLCCpgUhTMWNe0wSX0dt4SzrZLmpHWItQYMcM2XhO5IUgZ8TttA1ltyLLOUrszZgC0Et+7SVFnTurv1Zg9HzgBlKNQ4ckBRUzF9AePpzjMeZm6lGC9Au0rs6USxLdSg2Il2Jo4YMycxvGs1YXTrF4XACESilDk0oDmY2Rd5UwZKaAdkHQM9Tmc+cHS7nQADNUogJYClGdgRs8FVU5AZamqIHZbvkqklRm4VOew21A9czA8iehIpMmFx3cIw8jid4eixySzJDsQXYg1o1KUgS0XOhiQ41pBHRPAFauDeHlChVtBCAEhBGakgJJLuSdD4w5uW98KlJWdNRh8w/jSFU+xnCQySd6vp8vWPLvnIAWmYgKLFKQe85FMJzd4A98Br/AI7VyPLeMRcHOBrRZwEk1bR2f6rEBWpku2WYNC1HH1w0iabNBTVuEOwknHJnyhKEsCW0sMt4hteQieegtlA80dnI6QnY1k0a+kDRZJc90JOTiK5D3rAmUkDaCUSxkHqVlIxdpYMKmB5UztdqN7Oh6mDPsobKL5lIA3GHAxu0AqRhNXbzp8YJnTwuZNO6i3iSfjCSWgpyMNbBaARUB384LD7k2L2PFbiguaQpyYPvC2uizJSe7hURpQsPSAsadojUASM9B6wWVaePwL13STf5LpclsabiPdAUGc541AD2DRJeAkz5pXMJPZJlpfCQewpRSWPaZJz9HMJ7tmsjrPzqU3BClzP/ABjS2FKQhSMxhwkVokGo8YybbFCWX8Gyk9vHvkjvGypASMaptnWlSkFAA6vDWYkuDhaqsLnMsAIrllV1HZSoqlkuhT6N3SA7KD8iOcXi6pkuYqYiZKQDMZQcCpAryVV3zpwiv9Iej6pXZSqWJa1gScb4kqJm9h8mUpDCn/EfQxaNmft6O8LKy+zz+lj+EfxGMin/AGs8f4TGQXewHgL/AMzpPQ7+xX/dPvEF590+MZGQzV0I6vv9srtohHeMexkdb0W03YjPyh3YcxyjIyFqDR1fRZ7gyXzT7mNL076ucZGRoRMaXYtOfhHqsvKPIyLehf1IFwivPveIjIyE7/tHdL9wae8rw9o3tOQjIyBw/qC2/wBiBp+SYHnf2fgn3EZGQtLsap6YvhorupjIyC19Miz0JrLDVOUZGQxX0Zt/ZFpEljzjIyCrsHLpjKJZeaeY94yMg3oKL7l8jqxf7v4z/wDTMgS6P7Gzcz/rVGRkYWq+z9m/H7/0xjZv94lf3k+8R/tQ/wBymf8A3Sv9CoyMimm+0Y/xRyqMjIyGCD//2Q==")
				.param("email", "a@us.es").param("phoneNumber", "677889900"))
				.andExpect(status().is2xxSuccessful()).andExpect(view().name("actor/edit"));
	}

}

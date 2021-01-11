package com.project.TabernasSevilla.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.CustomerRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository costumerRepo;
	@Autowired
	private EstablishmentService establishmentService;
	
	@Autowired
	private UserService userService;
	
	public Customer findById(final int id) {
		return costumerRepo.findById(id);
	}
	
	public Customer create() {
		Customer customer = new Customer();
		return customer;
	}
	
	public Customer save(Customer customer) {
		return this.costumerRepo.save(customer);
	}
	
	public Customer setPreferredEstablishment(int establishmentId) {
		Customer cust = this.costumerRepo.findActorByUser(this.userService.getPrincipal().getUsername());
		cust.setFavEstablishment(this.establishmentService.findById(establishmentId));
		 Customer saved = this.save(cust);
		 return saved;		
	}
	
	public Customer register(final RegisterForm form) {
		Customer customer = create();
		customer.setId(0);
		customer.setEmail(form.getForm().getEmail());
		customer.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			customer.setAvatar(form.getForm().getAvatar());
		}else {
			customer.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		customer.setName(form.getForm().getName());
		customer.setSurname(form.getForm().getSurname());
		customer.setPhoneNumber(form.getForm().getPhoneNumber());
		customer.setFavEstablishment(null);
		User user = this.userService.createUser("CUSTOMER");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		User savedUser = this.userService.saveAndFlush(user);
		customer.setUser(savedUser);
		
		Customer saved = save(customer);
		return saved;
	}
}

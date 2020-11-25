package com.project.TabernasSevilla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Costumer;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.CostumerRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
public class CustomerService {

	@Autowired
	private CostumerRepository costumerRepo;
	
	@Autowired
	private UserService userService;
	
	public Costumer findById(final int id) {
		return costumerRepo.findById(id);
	}
	
	public Costumer create() {
		Costumer costumer = new Costumer();
		return costumer;
	}
	
	public Costumer register(final RegisterForm form) {
		Costumer costumer = create();
		costumer.setEmail(form.getForm().getEmail());
		costumer.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			costumer.setAvatar(form.getForm().getAvatar());
		}else {
			costumer.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		costumer.getUser().setUsername(form.getForm().getUsername());
		costumer.setName(form.getForm().getName());
		costumer.setSurname(form.getForm().getSurname());
		costumer.setPhoneNumber(form.getForm().getPhoneNumber());

		User user = this.userService.createUser("COSTUMER");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		costumer.setUser(user);
		
		Costumer saved = this.costumerRepo.save(costumer);
		return saved;
	}
}

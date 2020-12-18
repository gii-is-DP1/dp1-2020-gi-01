package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.domain.Waiter;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.WaiterRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class WaiterService {

	@Autowired
	private WaiterRepository waiterRepo;
	@Autowired
	private UserService userService;
	
	public Waiter create() {
		Waiter res = new Waiter();
		return res;
	}
	
	public Waiter findById(int id) {
		return this.waiterRepo.findById(id);
	}
	
	public List<Waiter> findAll(){
		return this.waiterRepo.findAll();
	}
	
	public Waiter save(Waiter waiter) {
		return this.waiterRepo.save(waiter);
	}
	
	public Waiter register(final RegisterForm form) {
		Waiter waiter = create();
		waiter.setId(0);
		waiter.setEmail(form.getForm().getEmail());
		waiter.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			waiter.setAvatar(form.getForm().getAvatar());
		}else {
			waiter.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		waiter.setName(form.getForm().getName());
		waiter.setSurname(form.getForm().getSurname());
		waiter.setPhoneNumber(form.getForm().getPhoneNumber());

		User user = this.userService.createUser("WAITER");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		User savedUser = this.userService.saveAndFlush(user);
		waiter.setUser(savedUser);
		
		Waiter saved = save(waiter);
		return saved;
	}
	
}

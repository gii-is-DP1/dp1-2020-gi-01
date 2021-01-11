package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Cook;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.CookRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class CookService {

	@Autowired
	private CookRepository cookRepo;
	@Autowired
	private UserService userService;
	
	public Cook create() {
		Cook res = new Cook();
		return res;
	}
	
	public Cook findById(int id) {
		return this.cookRepo.findById(id);
	}
	
	public List<Cook> findAll(){
		return this.cookRepo.findAll();
	}
	
	public Cook save(Cook cook) {
		return this.cookRepo.save(cook);
	}
	
	public Cook register(final RegisterForm form) {
		Cook cook = create();
		cook.setId(0);
		cook.setEmail(form.getForm().getEmail());
		cook.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			cook.setAvatar(form.getForm().getAvatar());
		}else {
			cook.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		cook.setName(form.getForm().getName());
		cook.setSurname(form.getForm().getSurname());
		cook.setPhoneNumber(form.getForm().getPhoneNumber());

		User user = this.userService.createUser("COOK");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		User savedUser = this.userService.saveAndFlush(user);
		cook.setUser(savedUser);
		
		Cook saved = save(cook);
		return saved;
	}
	
}

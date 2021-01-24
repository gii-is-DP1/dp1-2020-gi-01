package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Manager;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.ManagerRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class ManagerService {

 
	private ManagerRepository managerRepo;
 
	private UserService userService;
	
	@Autowired
	public ManagerService(ManagerRepository managerRepo, UserService userService) {
		super();
		this.managerRepo = managerRepo;
		this.userService = userService;
	}

	public Manager create() {
		Manager res = new Manager();
		return res;
	}
	
	public Manager findById(int id) {
		return this.managerRepo.findById(id);
	}
	
	public List<Manager> findAll(){
		return this.managerRepo.findAll();
	}
	
	public Manager save(Manager manager) {
		return this.managerRepo.save(manager);
	}
	
	public Manager register(final RegisterForm form) {
		Manager manager = create();
		manager.setId(0);
		manager.setEmail(form.getForm().getEmail());
		manager.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			manager.setAvatar(form.getForm().getAvatar());
		}else {
			manager.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		manager.setName(form.getForm().getName());
		manager.setSurname(form.getForm().getSurname());
		manager.setPhoneNumber(form.getForm().getPhoneNumber());

		User user = this.userService.createUser("MANAGER");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		User savedUser = this.userService.saveAndFlush(user);
		manager.setUser(savedUser);
		
		Manager saved = save(manager);
		return saved;
	}
}

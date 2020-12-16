package com.project.TabernasSevilla.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.AdminRepository;
import com.project.TabernasSevilla.security.User;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private UserService userService;
	
	public Admin create() {
		Admin res = new Admin();
		return res;
	}
	
	public List<Admin> findAll(){
		return this.adminRepo.findAll();
	}
	
	public Admin save(Admin admin) {
		return this.adminRepo.save(admin);
	}
	
	public Admin register(final RegisterForm form) {
		Admin admin = create();
		admin.setId(0);
		admin.setEmail(form.getForm().getEmail());
		admin.setPhoneNumber(form.getForm().getPhoneNumber());
		if (form.getForm().getAvatar() != "") {
			admin.setAvatar(form.getForm().getAvatar());
		}else {
			admin.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		}
		admin.setName(form.getForm().getName());
		admin.setSurname(form.getForm().getSurname());
		admin.setPhoneNumber(form.getForm().getPhoneNumber());

		User user = this.userService.createUser("ADMIN");
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(form.getPassword()));
		user.setUsername(form.getUsername());
		User savedUser = this.userService.saveAndFlush(user);
		admin.setUser(savedUser);
		
		Admin saved = save(admin);
		return saved;
	}
	
}

package com.project.TabernasSevilla.service;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.forms.ActorForm;
import com.project.TabernasSevilla.forms.RegisterForm;
import com.project.TabernasSevilla.repository.AbstractActorRepository;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class ActorService {

 
	private AbstractActorRepository<Actor> actorRepo;
 
	private RegKeyService regKeyService;
 
	private CustomerService customerService;
 
	private ManagerService managerService;
 
	private AdminService adminService;
 
	private CookService cookService;
 
	private WaiterService waiterService;
 
	private UserService userService;
	
	
	@Autowired
	public ActorService(AbstractActorRepository<Actor> actorRepo, RegKeyService regKeyService,
			CustomerService customerService, ManagerService managerService, AdminService adminService,
			CookService cookService, WaiterService waiterService, UserService userService) {
		super();
		this.actorRepo = actorRepo;
		this.regKeyService = regKeyService;
		this.customerService = customerService;
		this.managerService = managerService;
		this.adminService = adminService;
		this.cookService = cookService;
		this.waiterService = waiterService;
		this.userService = userService;
	}

	public ActorService() {
		super();
	}
	
	public Actor save(Actor actor) {
		return this.actorRepo.save(actor);
	}
	
	public Actor findById(final int id) {
		return actorRepo.findById(id);
	}
	
	public ActorForm formatForm(final Actor actor) {
		final ActorForm res = new ActorForm();
		res.setName(actor.getName());
		res.setSurname(actor.getSurname());
		res.setEmail(actor.getEmail());
		res.setAvatar(actor.getAvatar());
		res.setPhoneNumber(actor.getPhoneNumber());
		res.setId(actor.getId());
		res.setUsername(actor.getUser().getUsername());
		return res;
	}
	
	public Actor parseForm(final ActorForm form) {
		final Actor res;
		res = this.findById(form.getId());
		res.setName(form.getName());
		res.setSurname(form.getSurname());
		res.setEmail(form.getEmail());
		res.setAvatar(form.getAvatar());
		res.setPhoneNumber(form.getPhoneNumber());
		return res;
	}
	
	public void register(final RegisterForm form) {
		if(this.regKeyService.checkKey(form.getKey())) {
			Authority auth = this.regKeyService.findById(form.getKey()).getAuthority();
			switch(auth.getAuthority()) {
			case "MANAGER":
				this.managerService.register(form);
				break;
			case "ADMIN":
				this.adminService.register(form);
				break;
			case "WAITER":
				this.waiterService.register(form);
				break;
			case "COOK":
				this.cookService.register(form);
				break;
			}
				
		}else {
			this.customerService.register(form);
		}
	}
	
	
	
	public Actor getPrincipal() {
		return this.findByUsername(this.userService.getPrincipal().getUsername());
	}
	
	public Actor findByUsername(String username) {
		return this.actorRepo.findActorByUser(username);
	}

	public Collection<String> getAuthority(final Actor actor) {
		final Collection<Authority> auth = actor.getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}
}

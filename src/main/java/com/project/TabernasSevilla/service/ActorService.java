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

@Service
@Transactional
public class ActorService {

	@Autowired
	private AbstractActorRepository<Actor> actorRepo;
	@Autowired
	private RegKeyService regKeyService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CookService cookService;
	@Autowired
	private WaiterService waiterService;
	
	public ActorService() {
		super();
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
			case "ADMIN":
				this.adminService.register(form);
			case "WAITER":
				this.waiterService.register(form);
			case "COOK":
				this.cookService.register(form);
			}
				
		}else {
			this.customerService.register(form);
		}
	}
	


	public Collection<String> getAuthority(final Actor actor) {
		final Collection<Authority> auth = actor.getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}
}

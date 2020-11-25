package com.project.TabernasSevilla.service;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.forms.ActorForm;
import com.project.TabernasSevilla.repository.ActorRepository;
import com.project.TabernasSevilla.security.Authority;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository actorRepo;
	
	@Autowired
	private UserService userService;
	
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
	
	public Actor initialize(final Actor actor, final String authority) {
		actor.setAvatar("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setUser(userService.createUser(authority));

		return actor;
	}
	

	public void assertPrincipalAuthority(final String auth) {
		Assert.isTrue(this.getPrincipalAuthority().contains(auth), "The user logged does not have authority to do this action.");

	}

	public Actor findPrincipal() {
		return this.actorRepo.findByUser(UserService.getPrincipal().getId());
	}

	public Collection<String> getPrincipalAuthority() {
		final Collection<Authority> auth = this.findPrincipal().getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}

	public Collection<String> getAuthority(final Actor actor) {
		final Collection<Authority> auth = actor.getUser().getAuthorities();
		final Collection<String> res = new HashSet<>();
		for (final Authority a : auth)
			res.add(a.getAuthority());
		return res;
	}
}

package com.project.TabernasSevilla.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository authRepo;

	// Values -----------------------------------------------------------------

	public static final Set<String> authorities = new HashSet<>(Arrays.asList("CUSTOMER", "ADMIN"));


	public Authority saveAuthority(Authority authority) {
		return this.authRepo.save(authority);
	}
	
	public List<Authority> findAll() {
		return this.authRepo.findAll();
	}

	public Authority findByName(String authority) {
		Authority auth = this.authRepo.findByName(authority);
		if (auth == null) {
			auth = saveAuthority(create(authority));
		}
		return auth;
	}

	public Authority create(String authority) {
		Assert.isTrue(authorities.contains(authority),"Error creating authority: New authority is not compatible with the model");
		Authority res = new Authority();
		res.setAuthority(authority);
		return res;
	}
	
	public Collection<Authority> listAuthorities() {
		return this.authRepo.findAll();
	}
}

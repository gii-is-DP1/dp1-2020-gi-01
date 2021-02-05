package com.project.TabernasSevilla.service;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.RegKey;
import com.project.TabernasSevilla.repository.RegKeyRepository;
import com.project.TabernasSevilla.security.AuthorityService;
import com.project.TabernasSevilla.security.UserService;

@Service
@Transactional
public class RegKeyService {

 
	private RegKeyRepository regKeyRepo;
 
	private UserService userService;
 
	private AuthorityService authService;
	
	@Autowired
	public RegKeyService(RegKeyRepository regKeyRepo, UserService userService, AuthorityService authService) {
		super();
		this.regKeyRepo = regKeyRepo;
		this.userService = userService;
		this.authService = authService;
	}

	//CRUD
	public List<RegKey> findAll() {
		return this.regKeyRepo.findAll();
	}
	
	public RegKey findById(String key) {
		return this.regKeyRepo.findById(key).get();
	}
	
	public RegKey save(RegKey regKey) {
		Assert.isTrue(userService.getPrincipal().getAuthorities().contains(authService.findByName("ADMIN")),
				"Error creating regkey: insufficient authority");
		return this.regKeyRepo.save(regKey);
	}
	
	public RegKey create() {
		RegKey regKey = new RegKey();
		regKey.setKey(this.generateId());
		regKey.setAuthority(null);
		return regKey;
	}
	
	public void delete(String key) {
		Assert.isTrue(userService.getPrincipal().getAuthorities().contains(authService.findByName("ADMIN")),
				"Error deleting regkey: insufficient authority");
		RegKey regKey = this.regKeyRepo.findById(key).get();
		this.regKeyRepo.delete(regKey);
	}
	
	//OTHER METHODS
	
	//returns true is key exists
	public Boolean checkKey(String key) {
		return this.regKeyRepo.findById(key).isEmpty() ? false : true;
	}
	
	//generate random string key of length 10
	public String generateId() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String res = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return res;
	}
}

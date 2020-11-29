package com.project.TabernasSevilla.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AuthorityService authService;

	public UserService() {
		super();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User appUser = userRepo.findByUsername(username);
		if (appUser == null) { 
			throw new  UsernameNotFoundException(username); 
		}

		List<GrantedAuthority> grantList = new ArrayList<>();
		for (Authority authority : appUser.getAuthorities()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}

		UserDetails user = (UserDetails) new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), grantList);
		return user;

	}

	public User save(User user) {
		return this.userRepo.save(user);
	}

	public User saveAndFlush(User user) {
		return this.userRepo.saveAndFlush(user);
	}

	public User createUser(final String authority) {
		final User user = new User();

		final Set<Authority> auths = user.getAuthorities();
		final Authority auth = this.authService.findByName(authority);
		auths.add(auth);

		user.setAuthorities(auths);
		return user;
	}

	public static User getPrincipal() {
		User result;
		SecurityContext context;
		Authentication authentication;
		Object principal;

		context = SecurityContextHolder.getContext();
		Assert.notNull(context, "Error on getPrincipal: context not found");
		authentication = context.getAuthentication();
		Assert.notNull(authentication, "Error on getPrincipal: authentication not found");
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof User, "Error on getPrincipal: not an instance of User");
		result = (User) principal;
		Assert.notNull(result, "Error on getPrincipal: error on casting");

		return result;
	}

	public boolean checkPassword(final String pass) {
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = getPrincipal();
		return encoder.matches(pass, user.getPassword());
	}
}

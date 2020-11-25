package com.project.TabernasSevilla.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	public UserService() {
		super();
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
	
	
	
	public User createUser(final String authority) {
		final User user = new User();

		final Collection<Authority> auths = new HashSet<>();
		final Authority auth = new Authority();
		auth.setAuthority(authority);
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
		Assert.notNull(context,"Error on getPrincipal: context not found");
		authentication = context.getAuthentication();
		Assert.notNull(authentication,"Error on getPrincipal: authentication not found");
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof User,"Error on getPrincipal: not an instance of User");
		result = (User) principal;
		Assert.notNull(result,"Error on getPrincipal: error on casting");
		Assert.isTrue(result.getId() != 0, "Error on getPrincipal: id does not correspond with existing user");

		return result;
	}
	
	public boolean checkPassword(final String pass) {
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = getPrincipal();
		return  encoder.matches(pass, user.getPassword());
	}
}

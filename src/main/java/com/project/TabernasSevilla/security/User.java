package com.project.TabernasSevilla.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.project.TabernasSevilla.domain.BaseEntity;

@Entity
public class User extends BaseEntity implements UserDetails{

	
	private static final long serialVersionUID = -7445195410938494473L;
	
	public User() {
		super();
		this.authorities = new ArrayList<Authority>();
	}
	
	private String username;
	private String password;
	private Collection<Authority> authorities;

	@Override
	@NotEmpty
	@ElementCollection
	public Collection<Authority> getAuthorities() {
		return this.authorities;
	}
	
	public void setAuthorities(final Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	@Size(min = 5, max = 32)
	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(final String password) {
		this.password = password;
	}

	@Size(min = 5, max = 32)
	@Column(unique = true)
	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

	public void addAuthority(final Authority authority) {
		Assert.notNull(authority, "Cannot add NULL authority");
		Assert.isTrue(!this.authorities.contains(authority),"Cannot add authority: actor already has authority");

		this.authorities.add(authority);
	}

	public void removeAuthority(final Authority authority) {
		Assert.notNull(authority, "Cannot remove NULL authority");
		Assert.isTrue(this.authorities.contains(authority),"Cannot remove authority: actor does not have authority");

		this.authorities.remove(authority);
	}
	

}
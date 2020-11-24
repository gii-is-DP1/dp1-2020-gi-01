package com.project.TabernasSevilla.security;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.project.TabernasSevilla.domain.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "username")
	User user;
	
	@Size(min = 1, max = 50)
	String authority;
	
	
}

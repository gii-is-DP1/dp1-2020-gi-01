package com.project.TabernasSevilla.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.TabernasSevilla.security.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
}

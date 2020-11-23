package com.project.TabernasSevilla.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.TabernasSevilla.model.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
}

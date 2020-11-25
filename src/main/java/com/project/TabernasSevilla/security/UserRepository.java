package com.project.TabernasSevilla.security;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User,Integer>{

	User findByUsername(String username);
	User findById(int id);

}

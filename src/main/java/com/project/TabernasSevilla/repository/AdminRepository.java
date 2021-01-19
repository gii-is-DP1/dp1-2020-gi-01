package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;

import com.project.TabernasSevilla.domain.Admin;

public interface AdminRepository extends AbstractActorRepository<Admin>{
	
	Admin findById(int id) throws DataAccessException;
}

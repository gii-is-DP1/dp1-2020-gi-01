package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Admin;
import com.project.TabernasSevilla.domain.Customer;

public interface AdminRepository extends AbstractActorRepository<Admin>{
	
	Admin findById(int id) throws DataAccessException;
}

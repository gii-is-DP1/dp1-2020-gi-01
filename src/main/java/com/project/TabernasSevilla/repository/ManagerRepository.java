package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Customer;
import com.project.TabernasSevilla.domain.Manager;

public interface ManagerRepository extends AbstractActorRepository<Manager>{
	
	Manager findById(int id) throws DataAccessException;
}

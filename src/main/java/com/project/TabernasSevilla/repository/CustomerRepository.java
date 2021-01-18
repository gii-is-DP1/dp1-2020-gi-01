package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;

import com.project.TabernasSevilla.domain.Customer;

public interface CustomerRepository extends AbstractActorRepository<Customer>{
	
	Customer findById(int id) throws DataAccessException;
}

package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
	Customer findById(int id) throws DataAccessException;
}

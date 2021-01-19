package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;

import com.project.TabernasSevilla.domain.Manager;

public interface ManagerRepository extends AbstractActorRepository<Manager>{
	
	Manager findById(int id) throws DataAccessException;
}

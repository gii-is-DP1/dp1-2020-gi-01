package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.project.TabernasSevilla.domain.Costumer;

public interface CostumerRepository extends Repository<Costumer,Integer>{

	Costumer save(Costumer costumer) throws DataAccessException;
	
	Costumer findById(int id) throws DataAccessException;
}

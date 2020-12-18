package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.JobApplication;

public interface ContactRepository extends JpaRepository<JobApplication,Integer>{
	
	JobApplication findById(int id) throws DataAccessException;

}

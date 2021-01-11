package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum,Integer>{
	
	Curriculum findById(int id) throws DataAccessException;

}

package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Actor;

public interface ActorRepository extends JpaRepository<Actor,Integer>{

	Actor findById(int id) throws DataAccessException;

}

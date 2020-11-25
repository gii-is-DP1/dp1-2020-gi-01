package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.project.TabernasSevilla.domain.Actor;

public interface ActorRepository extends Repository<Actor,Integer>{

	Actor findById(int id) throws DataAccessException;
	
	@Query(nativeQuery=true,
			value="SELECT * FROM ACTOR a WHERE a.userid = :id")
	Actor findByUser(@Param("id") int id);
	
}

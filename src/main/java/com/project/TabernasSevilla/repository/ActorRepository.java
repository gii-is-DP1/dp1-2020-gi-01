package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.TabernasSevilla.domain.Actor;

public interface ActorRepository extends JpaRepository<Actor,Integer>{

	Actor findById(int id) throws DataAccessException;
	
	@Query(nativeQuery=true,
			value="SELECT * FROM ACTOR a WHERE a.userid = :id")
	Actor findByUser(@Param("id") int id);
	
}

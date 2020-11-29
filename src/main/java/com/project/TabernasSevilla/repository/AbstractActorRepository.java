package com.project.TabernasSevilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Actor;

public interface AbstractActorRepository<T extends Actor>  extends JpaRepository<T,Integer>{

	 @Query("SELECT a FROM #{#entityName} a WHERE a.user.username = ?1")
	 T findActorByUser(String username);
	 
	 @Query("SELECT a FROM #{#entityName} a WHERE a.id = ?1")
	 T findById(int id);
}

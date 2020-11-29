package com.project.TabernasSevilla.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository  extends JpaRepository<Authority,Integer>{
	
	@Query("SELECT a FROM Authority a WHERE a.authority = ?1")
	Authority findByName(String auth);

}

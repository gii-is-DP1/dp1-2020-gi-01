package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion,Integer>{

	@Query("SELECT p FROM Promotion p WHERE p.establishment.id = ?1")
	public List<Promotion> findByEstablishment(int establishmentId);
	
}

package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{

	@Query("SELECT r FROM Review r WHERE r.dish.id = ?1")
	public List<Review> findByDish(int dishId);
	
	@Query("SELECT r FROM Review r WHERE r.booking.establishment.id = ?1")
	public List<Review> findByEstablishment(int establishmentId);
}

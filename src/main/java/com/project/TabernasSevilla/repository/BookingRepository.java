package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	
	@Query("SELECT b FROM Booking b WHERE b.establishment.id = ?1")
	public List<Booking> findByEstablishment(int establishmentId);
	
	//TODO: return all for establishment where reservation date > today's date
}

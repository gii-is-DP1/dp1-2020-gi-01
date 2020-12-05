package com.project.TabernasSevilla.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	
	Booking findById(int id) throws DataAccessException;

}

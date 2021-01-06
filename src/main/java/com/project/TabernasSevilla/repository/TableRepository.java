package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.RestaurantTable;

public interface TableRepository extends JpaRepository<RestaurantTable,Integer>{

	@Query("SELECT t FROM RestaurantTable t WHERE t.establishment.id = ?1")
	public List<RestaurantTable> findByEstablishment(int establishmentId);
	
	@Query("SELECT SUM (t.occupied) FROM RestaurantTable t WHERE t.establishment.id = ?1")
	public Long countOccupiedByEstablishment(int establishmentId);

}

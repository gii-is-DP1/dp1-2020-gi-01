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
	
	@Query("SELECT COUNT(t) FROM RestaurantTable t WHERE t.establishment.id = ?1 AND t.hourSeated = NULL")
	public Long countFreeTables(int establishmentId);
	
	@Query("SELECT t FROM RestaurantTable t WHERE t.establishment.id = ?1 AND t.booking != NULL")
	public List<RestaurantTable> findBookedByEstablishment(int establishmentId);
	
	@Query("SELECT t.number FROM RestaurantTable t WHERE t.establishment.id = ?1 AND t.number= (SELECT MAX(t2.number) FROM RestaurantTable t2 WHERE t.establishment.id = ?1)")
	public Integer findBiggestTableNumber(int est);

	@Query("SELECT SUM(t.seating) FROM RestaurantTable t WHERE t.establishment.id = ?1 ")
	public Long getEstablishmentCapacity(int estId);
}

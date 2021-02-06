package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.RestaurantOrder;

public interface OrderRepository extends JpaRepository<RestaurantOrder,Integer>{
	
	@Query("SELECT o FROM RestaurantOrder o WHERE o.actor.id = ?1 AND o.status = 'DRAFT'")
	public RestaurantOrder findDraftByActor(int id);
	
	@Query("SELECT o FROM RestaurantOrder o WHERE o.actor.id = ?1 AND o.status != 'DRAFT' AND o.status != 'CLOSED' AND o.status != 'CANCELLED'")
	public List<RestaurantOrder> findActiveByActor(int id);
	
	@Query("SELECT o FROM RestaurantOrder o WHERE o.actor.id = ?1 AND (o.status = 'DRAFT' OR o.status = 'CLOSED' OR o.status = 'CANCELLED')")
	public List<RestaurantOrder> findInactiveByActor(int id);
	
	@Query("SELECT o FROM RestaurantOrder o WHERE o.establishment.id = ?1 AND o.status != 'DRAFT' AND o.status != 'CLOSED' AND o.status != 'CANCELLED'")
	public List<RestaurantOrder> findActiveByEstablishment(int id);
	
	@Query("SELECT o FROM RestaurantOrder o WHERE o.establishment.id = ?1 AND (o.status = 'DRAFT' OR o.status = 'CLOSED' OR o.status = 'CANCELLED')")
	public List<RestaurantOrder> findInactiveByEstablishment(int id);
}

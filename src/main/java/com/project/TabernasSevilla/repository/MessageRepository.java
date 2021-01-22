package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.Message;
import com.project.TabernasSevilla.domain.RestaurantOrder;

public interface MessageRepository extends JpaRepository<RestaurantOrder,Integer>{

	@Query("SELECT m FROM Message m WHERE m.actor.id = ?1 AND m.readDate = NULL")
	public List<Message> findUnreadByActor(int actorId);
	
	@Query("SELECT m FROM Message m WHERE m.actor.id = ?1 AND m.readDate != NULL ORDER BY m.deliveryDate DESC")
	public List<Message> findLatestReadByActor(int actorId, Pageable pageable);
}

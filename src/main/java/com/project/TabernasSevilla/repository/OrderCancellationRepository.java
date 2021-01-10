package com.project.TabernasSevilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.OrderCancellation;

public interface OrderCancellationRepository extends JpaRepository<OrderCancellation,Integer>{

	@Query("SELECT o FROM OrderCancellation o WHERE o.order.id = ?1")
	public OrderCancellation findByOrder(int orderId);
}

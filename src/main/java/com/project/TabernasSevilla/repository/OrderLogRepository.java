package com.project.TabernasSevilla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.TabernasSevilla.domain.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog,Integer> {

		@Query("SELECT o FROM OrderLog o WHERE o.order.id = ?1 ORDER BY o.moment ASC")
		public List<OrderLog> findByOrder(int orderId);
}

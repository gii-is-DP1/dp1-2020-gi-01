package com.project.TabernasSevilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Establishment;
import com.project.TabernasSevilla.domain.OrderCancellation;
import com.project.TabernasSevilla.domain.Review;

public interface OrderCancellationRepository extends JpaRepository<OrderCancellation,Integer>{

}

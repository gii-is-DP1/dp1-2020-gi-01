package com.project.TabernasSevilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}

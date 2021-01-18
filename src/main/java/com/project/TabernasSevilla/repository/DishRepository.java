package com.project.TabernasSevilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TabernasSevilla.domain.Dish;


public interface DishRepository extends JpaRepository<Dish, Integer> {

}

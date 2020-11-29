package com.project.TabernasSevilla.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.TabernasSevilla.domain.Dish;


public interface DishRepository extends CrudRepository<Dish, Integer> {

}

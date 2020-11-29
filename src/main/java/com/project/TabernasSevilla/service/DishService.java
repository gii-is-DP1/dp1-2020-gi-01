package com.project.TabernasSevilla.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.repository.DishRepository;

@Service
public class DishService {
	@Autowired
	private DishRepository dishRepository;

	@Transactional
	public int dishCount() throws DataAccessException {
		return (int) dishRepository.count();
	}

	@Transactional
	public Iterable<Dish> dishList() throws DataAccessException {
		return dishRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Dish> dishById(int id) throws DataAccessException {
		return dishRepository.findById(id);
	}

	@Transactional
	public void saveDish(Dish dish) throws DataAccessException {
		dishRepository.save(dish);
	}

	@Transactional
	public void deleteDish(Dish dish) throws DataAccessException {
		dishRepository.delete(dish);
	}

}

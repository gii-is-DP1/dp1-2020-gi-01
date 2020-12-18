package com.project.TabernasSevilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.repository.DishRepository;

@Service
@Transactional
public class DishService {
	@Autowired
	private DishRepository dishRepository;

	public int count() throws DataAccessException {
		return (int) dishRepository.count();
	}

	public List<Dish> findAll() throws DataAccessException {
		return dishRepository.findAll();
	}

	public Optional<Dish> findById(int id) throws DataAccessException {
		return dishRepository.findById(id);
	}

	public Dish save(Dish dish) throws DataAccessException {
		return dishRepository.save(dish);
	}

	public void delete(Dish dish) throws DataAccessException {
		dishRepository.delete(dish);
	}

}

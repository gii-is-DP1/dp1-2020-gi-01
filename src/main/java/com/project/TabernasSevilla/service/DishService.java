package com.project.TabernasSevilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Dish;
import com.project.TabernasSevilla.domain.Review;
import com.project.TabernasSevilla.repository.DishRepository;
import com.project.TabernasSevilla.repository.ReviewRepository;

@Service
@Transactional
public class DishService {
 
	private DishRepository dishRepository;
	
 
	private ReviewRepository reviewRepository;
	
	@Autowired
	public DishService(DishRepository dishRepository, ReviewRepository reviewRepository) {
		super();
		this.dishRepository = dishRepository;
		this.reviewRepository = reviewRepository;
	}

	public int count() throws DataAccessException {
		return (int) dishRepository.count();
	}

	public List<Dish> findAll() throws DataAccessException {
		return dishRepository.findAll();
	}

	public Optional<Dish> findById(int id) throws DataAccessException {
		return dishRepository.findById(id);
	}

	public Dish create() {
		Dish dish = new Dish();
		return dish;
	}
	/*
	public void actualizarScore(Dish dish) {
		List<Review> ls = reviewRepository.findByDish(dish.getId());
		//List<Review> ls = new ArrayList<Review>();
		Double rat = 0.0;
		if(!(ls.isEmpty())) {
			Integer n = 0;
			Double suma = 0.0;
			for(Review rev :ls) {
				n++;
				suma = suma + rev.getRating();
			}
			rat = suma/n;
		}
		dish.setScore(rat);
	}
	*/

	public Dish save(Dish dish) {
		Dish result = null;
		if (dish.getId() == 0) {
			result = dish;
		} else {
			result = this.dishRepository.findById(dish.getId()).get();
			result.setAllergens(dish.getAllergens());
			result.setDescription(dish.getDescription());
			result.setName(dish.getName());
			result.setPrice(dish.getPrice());


			try {
				List<Review> ls = reviewRepository.findByDish(dish.getId());
				//List<Review> ls = new ArrayList<Review>();
				Double rat = 0.0;
				if(!(ls.isEmpty())) {
					Integer n = 0;
					Double suma = 0.0;
					for(Review rev :ls) {
						n++;
						suma = suma + rev.getRating();
					}
					rat = suma/n;
				}
				result.setScore(rat);
			} catch (Exception e) {

				e.printStackTrace();
			}

			if (dish.getPicture() != "" || !dish.getPicture().isBlank()) {
				if (dish.getPicture().startsWith("http://") || dish.getPicture().startsWith("https://")) {
					result.setPicture(dish.getPicture());
				} else {
					// result.setPicture("http://" + dish.getPicture());
					throw new IllegalArgumentException();
				}
			}
		}
		result = this.dishRepository.save(result);
		return result;
	}

	public void delete(Dish dish) throws DataAccessException {
		dishRepository.delete(dish);
	}

}


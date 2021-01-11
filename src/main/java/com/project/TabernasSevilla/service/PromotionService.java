package com.project.TabernasSevilla.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.TabernasSevilla.domain.Promotion;
import com.project.TabernasSevilla.repository.PromotionRepository;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository promoRepository;

	@Transactional
	public int promotionCount() throws DataAccessException {
		return (int) promoRepository.count();
	}

	public Iterable<Promotion> promotionList() throws DataAccessException {
		return promoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Promotion> promotionById(int id) throws DataAccessException {
		return promoRepository.findById(id);
	}

	@Transactional
	public void savePromotion(Promotion promo) throws DataAccessException {
		promoRepository.save(promo);
	}

	@Transactional
	public void deletePromotion(Promotion promo) throws DataAccessException {
		promoRepository.delete(promo);
	}
}

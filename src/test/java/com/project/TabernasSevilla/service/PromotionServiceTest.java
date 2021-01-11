package com.project.TabernasSevilla.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.TabernasSevilla.domain.Promotion;

class PromotionServiceTest{
	@Autowired
	protected PromotionService promotionService;

	@Test
	public void shouldCreateAnInstanceCorrectly() {
		Promotion pro = new Promotion(); // job application
		pro.setCode("YD629DM");
		pro.setDescription("2x1 en hamburguesas de pavo");
		pro.setTitle("Hamburguers");
		pro.setUses(2);
		pro.setStartDate("2020-09-30");
		pro.setEndDate("2020-10-20");
		try {
			this.promotionService.savePromotion(pro);
		} catch (Exception e) {
			Logger.getLogger(PromotionServiceTest.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		assertThat(pro.getId()).isNotNull();
	}
}

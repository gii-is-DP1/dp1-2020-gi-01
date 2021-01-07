package com.project.TabernasSevilla.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.TabernasSevilla.domain.Promotion;
import com.project.TabernasSevilla.service.PromotionService;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

	@Autowired
	private PromotionService promoService;

	@GetMapping(path = "/{promotionId}")
	public String showPromotionInfo(@PathVariable("promotionId") int promotionId, ModelMap modelMap) {
		String view = "promotions/promotionInfo";
		Optional<Promotion> promotion = promoService.promotionById(promotionId);
		modelMap.addAttribute("promotion", promotion.get());
		return view;
	}

	@GetMapping()
	public String promotionList(ModelMap modelMap) {
		String view = "promotions/list";
		Iterable<Promotion> promotions = promoService.promotionList();
		modelMap.addAttribute("promotions", promotions);
		return view;

	}

	@GetMapping(path = "/new")
	public String createPromotion(ModelMap modelMap) {
		String view = "promotions/createPromotionForm";
		modelMap.addAttribute("promotion", new Promotion());
		return view;
	}

	@PostMapping(path = "/save")
	public String savePromotion(@Valid Promotion promotion, BindingResult result, ModelMap modelMap) {
		String view = "promotions/list";
		if (result.hasErrors()) {
			modelMap.addAttribute("promotion", promotion);
			return "promotions/createPromotionForm";
		} else {
			promoService.savePromotion(promotion);
			modelMap.addAttribute("message", "Promotion successfully saved");
			view = promotionList(modelMap);
		}
		return view;
	}

	@GetMapping(path = "/delete/{promotionId}")
	public String deletePromotion(@PathVariable("promotionId") int promotionId, ModelMap modelMap) {
		String view = "promotions/promotionList";
		Optional<Promotion> promotion = promoService.promotionById(promotionId);
		if (promotion.isPresent()) {
			promoService.deletePromotion(promotion.get());
			modelMap.addAttribute("message", "Promotion succesfully deleted");
			view = promotionList(modelMap);
		} else {
			modelMap.addAttribute("message", "Promotion not found");
			view = promotionList(modelMap);
		}
		return view;
	}

	@GetMapping(value = "{promotionId}/edit")
	public String updatePromotionForm(@PathVariable("promotionId") int promotionId, ModelMap modelMap) {
		String view = "promotions/updatePromotionForm";
		Optional<Promotion> promotion = this.promoService.promotionById(promotionId);
		modelMap.addAttribute(promotion.get());
		return view;
	}

	@PostMapping(value = "{promotionId}/edit")
	public String processUpdatePromotionForm(@Valid Promotion promotion, BindingResult result,
			@PathVariable("promotionId") int promotionId) {
		String view = "promotions/updatePromotionForm";
		if (result.hasErrors()) {
			return view;
		} else {
			promotion.setId(promotionId);
			this.promoService.savePromotion(promotion);
			return "redirect:/promotions/{promotionId}";
		}

	}

}

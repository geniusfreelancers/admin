package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.PromoCodes;

public interface PromoCodesService {
	
	PromoCodes findOne(Long id);
	
	PromoCodes findByPromoCode(String couponCode);

	List<PromoCodes> findAll();
	
	void removeOne(Long id);
}

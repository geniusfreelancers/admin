package com.adminportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.PromoCodes;

public interface PromoCodesRepository extends CrudRepository<PromoCodes, Long> {

	PromoCodes findByCouponCode(String couponCode);



}

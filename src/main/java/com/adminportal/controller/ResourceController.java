package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.ProductService;
import com.adminportal.service.PromoCodesService;

@RestController
public class ResourceController {

	@Autowired
	private ProductService productService;
	@Autowired
	private PromoCodesService promoCodesService;
	
	@RequestMapping(value="/product/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
		for(String id : productIdList){
			String productId = id.substring(8);
			productService.removeOne(Long.parseLong(productId));
		}
		
		return "delete success";
	}
	
	@RequestMapping(value="/coupons/allcoupons/removeList", method=RequestMethod.POST)
	public String removePromoList(
			@RequestBody ArrayList<String> productIdList, Model model
			){
		for(String id : productIdList){
			String productId = id.substring(8);
			promoCodesService.removeOne(Long.parseLong(productId));
		}
		
		return "delete success";
	}
}

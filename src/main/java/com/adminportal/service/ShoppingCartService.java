package com.adminportal.service;


import java.math.BigDecimal;

import com.adminportal.domain.PromoCodes;
import com.adminportal.domain.ShoppingCart;

public interface ShoppingCartService {
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
	ShoppingCart findCartByBagId(String bagId);
	void remove(ShoppingCart shoppingCart);
	BigDecimal calculateCartSubTotal(ShoppingCart shoppingCart);
	BigDecimal calculateDiscountAmount(ShoppingCart shoppingCart, PromoCodes promoCodes);
	BigDecimal calculateCartOrderTotal(ShoppingCart shoppingCart); 
	BigDecimal calculateShippingCost(ShoppingCart shoppingCart);
	PromoCodes checkCouponValidity(PromoCodes promoCodes);
/*	GuestShoppingCart updateGuestShoppingCart(GuestShoppingCart guestShoppingCart);
	
	GuestShoppingCart findByGuestShoppingCartId(String id);*/
}

package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.Order;
import com.adminportal.domain.Payment;
import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.User;

public interface OrderService {
	
	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user,String email, String phone);
	/*Order createGuestOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, String phone, String email);*/
	
	Order findOne(Long id);
	List<Order> findAll();
	List<Order> findByUser(User user);
	List<Order> findAllByOrderDateDesc();
}

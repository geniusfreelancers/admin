package com.adminportal.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.domain.Payment;
import com.adminportal.domain.Product;
import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.User;
import com.adminportal.repository.OrderRepository;
import com.adminportal.service.CartItemService;
import com.adminportal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, 
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user,
			String email, String phone
			){
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		String orderEmail = email;
		String orderPhone = phone;
		if(user != null) {
			orderEmail = user.getEmail();
			orderPhone = user.getPhone();
		}
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList){
			Product product = cartItem.getProduct();
			cartItem.setOrder(order);
			product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
		}
		order.setOrderEmail(orderEmail);
		order.setOrderPhone(orderPhone);
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}

/*	public synchronized Order createGuestOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, String phone, String email) {
		
	}*/
	
	public Order findOne(Long id){
		return orderRepository.findOne(id);
	}
	
	public List<Order> findAll(){
		return (List<Order>) orderRepository.findAll();
	}
	public List<Order> findByUser(User user){
		return orderRepository.findByUserId(user.getId());
	}
	
	public List<Order> findAllByOrderDateDesc(){
		return orderRepository.findAllByOrderByOrderDateDesc();
	}
}

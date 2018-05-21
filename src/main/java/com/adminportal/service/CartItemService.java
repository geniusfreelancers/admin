package com.adminportal.service;

import java.util.List;



import com.adminportal.domain.CartItem;

import com.adminportal.domain.Order;
import com.adminportal.domain.Product;
import com.adminportal.domain.ShoppingCart;


public interface CartItemService {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem addProductToCartItem(Product product,ShoppingCart shoppingCart,int qty,String size);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
	
	//Guest Cart Added
	ShoppingCart findGuestCartBySessionId(String sessionid);
	
	/*List<CartItem> findByGuestShoppingCart(GuestShoppingCart guestShoppingCart);*/
	

	
}

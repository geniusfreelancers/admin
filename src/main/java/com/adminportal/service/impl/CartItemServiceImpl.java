package com.adminportal.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.CartItem;

import com.adminportal.domain.Order;
import com.adminportal.domain.Product;
import com.adminportal.domain.PromoCodes;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.SiteSetting;
import com.adminportal.repository.CartItemRepository;


import com.adminportal.repository.PromoCodesRepository;
import com.adminportal.repository.ShoppingCartRepository;
import com.adminportal.service.CartItemService;
import com.adminportal.service.PromoCodesService;
import com.adminportal.service.ShoppingCartService;
import com.adminportal.service.SiteSettingService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	

	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private SiteSettingService siteSettingService;
	
	@Autowired
	private PromoCodesService promoCodesService;
	
	@Autowired
	private PromoCodesRepository promoCodesRepository;
	
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart){
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}
	
	public CartItem updateCartItem(CartItem cartItem){
		BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;		
	}
	
	public CartItem addProductToCartItem(Product product,ShoppingCart shoppingCart,int qty,String size){
		List<CartItem> cartItemList = findByShoppingCart(shoppingCart);
		PromoCodes promoCodes = promoCodesService.findByPromoCode(shoppingCart.getPromoCode());
		
		for(CartItem cartItem : cartItemList){
			if(product.getId() == cartItem.getProduct().getId()){
				//Check if product with same size already exist in cart
				if(cartItem.getProductSize().equalsIgnoreCase(size)) {
					cartItem.setQty(qty);
        			updateCartItem(cartItem);
						//	cartTotal = cartTotal.add(cartItem.getSubtotal());
					
					
			/*		shoppingCart.setGrandTotal(cartTotal);
				cartItem.setSubtotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(qty)).setScale(2, BigDecimal.ROUND_HALF_UP));
			*/	cartItemRepository.save(cartItem);
			//	shoppingCartRepository.save(shoppingCart);
				shoppingCart.setGrandTotal(shoppingCartService.calculateCartSubTotal(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
				shoppingCart.setDiscountedAmount(shoppingCartService.calculateDiscountAmount(shoppingCart, promoCodesRepository.findByCouponCode(shoppingCart.getPromoCode())).setScale(2, BigDecimal.ROUND_HALF_UP));
				shoppingCart.setShippingCost(shoppingCartService.calculateShippingCost(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
				shoppingCart.setOrderTotal(shoppingCartService.calculateCartOrderTotal(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
		       	Date addedDate = Calendar.getInstance().getTime();
				shoppingCart.setUpdatedDate(addedDate);
		       	shoppingCartRepository.save(shoppingCart);
				return cartItem;
				}
			}
		}

		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(shoppingCart);
		cartItem.setProduct(product);
		cartItem.setProductSize(size);
		cartItem.setQty(qty);
		BigDecimal itemSubTotal = new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(qty)).setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubtotal(itemSubTotal);
		
		cartItem = cartItemRepository.save(cartItem);
		updateCartItem(cartItem);
/*		ProductToCartItem productToCartItem = new ProductToCartItem();
		productToCartItem.setProduct(product);
		productToCartItem.setCartItem(cartItem);
		productToCartItemRepository.save(productToCartItem);*/

		Date addedDate = Calendar.getInstance().getTime();
		shoppingCart.setUpdatedDate(addedDate);
		
       	shoppingCartRepository.save(shoppingCart);
		shoppingCart.setGrandTotal(shoppingCartService.calculateCartSubTotal(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
		shoppingCart.setDiscountedAmount(shoppingCartService.calculateDiscountAmount(shoppingCart, promoCodes).setScale(2, BigDecimal.ROUND_HALF_UP));
		shoppingCart.setShippingCost(shoppingCartService.calculateShippingCost(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
		shoppingCart.setOrderTotal(shoppingCartService.calculateCartOrderTotal(shoppingCart).setScale(2, BigDecimal.ROUND_HALF_UP));
       
		shoppingCartRepository.save(shoppingCart);
		return cartItem;
	}
	
/*	public CartItem addProductToCartItem(Product product,User user,int qty){
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for(CartItem cartItem : cartItemList){
			if(product.getId() == cartItem.getProduct().getId()){
				cartItem.setQty(qty);
				//cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(qty)));
				cartItemRepository.save(cartItem);
				return cartItem;
				
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setProduct(product);
		
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal(qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		ProductToCartItem productToCartItem = new ProductToCartItem();
		productToCartItem.setProduct(product);
		productToCartItem.setCartItem(cartItem);
		productToCartItemRepository.save(productToCartItem);
		
		return cartItem;
	}*/
	
	public CartItem findById(Long id){
		return cartItemRepository.findOne(id);
	}
	
	public void removeCartItem(CartItem cartItem){
	//	productToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);	
	}
	
	public CartItem save(CartItem cartItem){
		return cartItemRepository.save(cartItem);
	}
	
	public List<CartItem> findByOrder(Order order){
		return cartItemRepository.findByOrder(order);
	}
	
	
	
	public ShoppingCart findGuestCartBySessionId(String sessionid) {
		return shoppingCartRepository.findBySessionId(sessionid);
	}


}

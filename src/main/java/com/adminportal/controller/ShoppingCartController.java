package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.adminportal.domain.CartItem;
import com.adminportal.domain.Order;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.ShoppingCartService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;
import com.adminportal.repository.CartItemRepository;
import com.adminportal.service.CartItemService;

@Controller
public class ShoppingCartController {
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@RequestMapping("/shoppingCarts")
	public String shoppingCartsList(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<ShoppingCart> cartList = shoppingCartService.findAllByOrderDateDesc();
		model.addAttribute("shoppingCartList", cartList);
		if(cartList == null) {
			model.addAttribute("emptyOrder", true);
			return "shoppingCartList";
		}else {
			model.addAttribute("emptyOrder", false);
		}
		return "shoppingCartList";
		
	}
	
	@RequestMapping("/shoppingCart/{cartId}")
	public String shoppingCart(@PathVariable(value = "cartId") int cartId, Model model,@AuthenticationPrincipal User activeUser){
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		ShoppingCart shoppingCart;
		List<CartItem> cartItemList = null;
		//User need to log in If wanted to implement Guest Check out need to work on this
			shoppingCart = shoppingCartService.findOne(new Long(cartId));
			System.out.println("SUCCESSFUL WITH CART ID");
	
       	if (shoppingCart == null || shoppingCart.getCartItemList().size() < 1) {
       		model.addAttribute("emptyCart",true);
       		

       	}else {
       		cartItemList = cartItemService.findByShoppingCart(shoppingCart);
       	
		//	shoppingCartService.updateShoppingCart(shoppingCart);
			model.addAttribute("emptyCart",false);	
		}
       	
		model.addAttribute("cartItemList",cartItemList);
		model.addAttribute("shoppingCart",shoppingCart);
		model.addAttribute("noCartExist",true);
		
		return "shoppingCart";
	}
	
	
	@RequestMapping("/rest/cart/{cartId}")
    public @ResponseBody
    ShoppingCart getCartById(@PathVariable(value = "cartId") Long cartId){
		ShoppingCart shoppingCart = shoppingCartService.findOne(new Long(cartId));
		System.out.println("SUCCESSFUL WITH ID LOGIC");
		/*if(shoppingCart.getId() == new Long(cartId)) {*/
			 return shoppingCart;
		/*}
        return null;*/
    }
	
	

	@RequestMapping(value= "/rest/cart/removeItem", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@RequestParam("id") Long id){
		cartItemService.removeCartItem(cartItemService.findById(id));
	}
	
	
	@RequestMapping(value = "/rest/cart/remove/{cartItemId}", method = RequestMethod.POST)
    public @ResponseBody ShoppingCart removeCartItems(@PathVariable(value = "cartItemId") Long id){
		CartItem cartItem = cartItemRepository.findOne(id);
		cartItemRepository.delete(cartItem);
		
		ShoppingCart shoppingCart = cartItem.getShoppingCart();
		shoppingCart = shoppingCartService.updateShoppingCart(shoppingCart);
		return shoppingCart;
    }
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload.")
    public void handleClientErrors(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error.")
    public void handleServerErrors(Exception e){}
}

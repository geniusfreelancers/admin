package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.Contact;
import com.adminportal.domain.Order;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.CategoryService;
import com.adminportal.service.ContactService;
import com.adminportal.service.NewsletterService;
import com.adminportal.service.OrderService;
import com.adminportal.service.ProductService;
import com.adminportal.service.ShoppingCartService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NewsletterService newsletterService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private AmazonSESEmail amazonSesEmail;
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
        model.addAttribute("user", user);
        amazonSesEmail.sendAWSEmail();
        List<Contact> contactList = contactService.findTop10ByOrderByIdDesc();
        List<Order> ordersList = orderService.findTop10ByOrderByIdDesc();
		
        List<ShoppingCart> shoppingCartList = shoppingCartService.findTop10ByOrderByUpdatedDateDesc();
		if(ordersList == null) {
			model.addAttribute("emptyOrder", true);
		}else {
			model.addAttribute("emptyOrder", false);
		}
		if(shoppingCartList == null) {
			model.addAttribute("emptyCart", true);
		}else {
			model.addAttribute("emptyCart", false);
		}
		Long userCount = userService.countUser();
		Long orderCount = orderService.orderCount();
		Long productCount = productService.productCount();
		Long categoryCount = categoryService.categoryCount();
		Long subCategoryCount = categoryService.subCategoryCount();
		Long subSubCategoryCount = categoryService.subSubCategoryCount();
		Long soldProductsCount = productService.soldProductsCount();
		Long subscribersCount = newsletterService.subscribersCount();
		 model.addAttribute("contactList", contactList);
		model.addAttribute("shoppingCartList", shoppingCartList);
		model.addAttribute("ordersList", ordersList);
		model.addAttribute("userCount",userCount);
		model.addAttribute("productCount",productCount);
		model.addAttribute("orderCount",orderCount);
		model.addAttribute("categoryCount",categoryCount);
		model.addAttribute("subCategoryCount",subCategoryCount);
		model.addAttribute("subSubCategoryCount",subSubCategoryCount);
		model.addAttribute("soldProductsCount",soldProductsCount);
		model.addAttribute("subscribersCount",subscribersCount);
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model){
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
		return "login";
	}
	

}


package com.adminportal.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.Contact;
import com.adminportal.domain.Newsletter;
import com.adminportal.domain.Order;
import com.adminportal.domain.OrderLog;
import com.adminportal.domain.Product;
import com.adminportal.domain.PromoCodes;
import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.StaticPage;
import com.adminportal.domain.User;
import com.adminportal.service.ContactService;
import com.adminportal.service.NewsletterService;
import com.adminportal.service.OrderLogService;
import com.adminportal.service.OrderService;
import com.adminportal.service.ProductService;
import com.adminportal.service.PromoCodesService;
import com.adminportal.service.ShoppingCartService;
import com.adminportal.service.StaticPageService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.AmazonClient;
import com.adminportal.service.impl.StripeService;
import com.stripe.model.Charge;

@Controller
@RequestMapping("/search")
public class SearchController {
	private AmazonClient amazonClient;
	
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @Autowired
    SearchController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    @Autowired
	private StripeService paymentsService;
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private PromoCodesService promoCodesService;
	@Autowired
	private StaticPageService staticPageService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private NewsletterService newsletterService;
	@Autowired
	private OrderLogService orderLogService;
	
	@RequestMapping("/searchBytype")
	public String searchBytype(@ModelAttribute("searchType") String searchType,
    		@ModelAttribute("keyword") String keyword,
			Model model,@AuthenticationPrincipal User activeUser){
				User user = userService.findByUsername(activeUser.getUsername());
				model.addAttribute("user", user);
				model.addAttribute("keyword", keyword);
				return "norecordfound";
	}
	
	@RequestMapping(value="/searchBytype", method=RequestMethod.POST)
	public String searchBytypePost(@ModelAttribute("searchType") String searchType,
    		@ModelAttribute("keyword") String keyword,
			Model model,@AuthenticationPrincipal User activeUser){
				User user = userService.findByUsername(activeUser.getUsername());
				model.addAttribute("user", user);
				model.addAttribute("keyword", keyword);
				if(!searchType.isEmpty() && !keyword.isEmpty()) {
				switch(searchType) {
		         case "products" :
		        	Product product = productService.findOne(Long.valueOf(keyword).longValue());
		        	String fileUrl = endpointUrl + "/" + bucketName + "/";
		        	if (product != null){
		        		String availableSize = product.getSize();
		        		List<String> sizeList = Arrays.asList(availableSize.split("\\s*,\\s*"));

		        		String productImages = product.getProductImagesName();
		        		List<String> productImageList = Arrays.asList(productImages.split("\\s*,\\s*"));
		        		
		        		if(product.getSoldProducts() != null){
		        			model.addAttribute("soldProduct", true);
		        		}else {
		        			model.addAttribute("soldProduct", false);
		        		}
		        		model.addAttribute("sizeList", sizeList);
		        		model.addAttribute("productImageList", productImageList);
			     		model.addAttribute("product", product);
			     		model.addAttribute("fileUrl", fileUrl);
			     		return "productInfo";
		        	}
		        	List<Product> productList = productService.searchProducts(keyword);
		        	if(productList == null || productList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
			     		model.addAttribute("productList", productList);
			     		model.addAttribute("fileUrl", fileUrl);
			     		return "productList";
		        	}
		         case "users" :
		        	 if(keyword.contains("@")&&keyword.contains(".")) {
		        		 User customer = userService.findByEmail(keyword);
		        		 model.addAttribute("customer",customer);
		        		 return "customerdetails";
		        	 }
		        	User customer1 = userService.findByUsername(keyword);
		        	
		        	if(customer1 != null) {
		        		model.addAttribute("customer",customer1);
		        		return "customerdetails";
		        	}
		        	 List<User> userList = userService.searchUsers(keyword);
		        	 if(userList == null || userList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
			        	 model.addAttribute("userList", userList);
			        	 return "customers";
		        	}
		         case "orders" :
		        	 Order order = orderService.findOne(Long.valueOf(keyword).longValue());
		        	 if(order != null) {
		        		 Charge charge;
		      	       try {  
		      	    	   String transactionId = order.getPaymentConfirm();
		      	           charge = Charge.retrieve(transactionId);
		      	          
		      	       } catch (Exception e) {
		      	           System.out.println("Exception: " + e);
		      	           return "badRequestPage";
		      	       }

		      	       model.addAttribute("isSuccess", charge.getStatus());
		      	       model.addAttribute("transaction", charge);
		      	   		LocalDate today = LocalDate.now();
		      	   		LocalDate estimatedDeliveryDate;
		      	   		
		      	   		if(order.getShippingMethod().equals("groundShipping")){
		      	   			estimatedDeliveryDate = today.plusDays(5);
		      	   		}else{
		      	   			estimatedDeliveryDate = today.plusDays(3);
		      	   		}
		      	   		model.addAttribute("creditMethod",true);
		      	   		int currentStatus = 1;
		      	   		if(order.getOrderStatus().equalsIgnoreCase("created")) {
		      	   			currentStatus = 2;
		      	   		}else if (order.getOrderStatus().equalsIgnoreCase("processing")) {
		      	   			currentStatus = 3;
		      	   		}else if (order.getOrderStatus().equalsIgnoreCase("shipped")) {
		      	   			currentStatus = 4;
		      	   		}else if (order.getOrderStatus().equalsIgnoreCase("intransit")) {
		      	   			currentStatus = 5;
		      	   		}else if (order.getOrderStatus().equalsIgnoreCase("delivered")) {
		      	   			currentStatus = 6;
		      	   		}else {
		      	   			currentStatus = 2;
		      	   		}
		      	   		model.addAttribute("estimatedDeliveryDate",estimatedDeliveryDate);
		      	   		model.addAttribute("order",order);
		      	   		model.addAttribute("currentStatus",currentStatus);
		      	   		model.addAttribute("cartItemList", order.getCartItemList());
		      	   		List<OrderLog> orderLogList = orderLogService.findByOrderByOrderByIdDesc(order);
		      	   		if(orderLogList.isEmpty()) {
		      	   			model.addAttribute("emptyLog",true);
		      	   		}else {
		      	   			model.addAttribute("emptyLog",false);
		      	   			model.addAttribute("orderLogList",orderLogList);
		      	   		}
			        	 return "orderdetails";
		        	 }
		        	 Order order1 = orderService.findByTrackingNumber(keyword);
		        	 if(order1 != null) {
		        		 Charge charge;
			      	       try {  
			      	    	   String transactionId = order1.getPaymentConfirm();
			      	           charge = Charge.retrieve(transactionId);
			      	          
			      	       } catch (Exception e) {
			      	           System.out.println("Exception: " + e);
			      	           return "badRequestPage";
			      	       }

			      	       model.addAttribute("isSuccess", charge.getStatus());
			      	       model.addAttribute("transaction", charge);
			      	   		LocalDate today = LocalDate.now();
			      	   		LocalDate estimatedDeliveryDate;
			      	   		
			      	   		if(order1.getShippingMethod().equals("groundShipping")){
			      	   			estimatedDeliveryDate = today.plusDays(5);
			      	   		}else{
			      	   			estimatedDeliveryDate = today.plusDays(3);
			      	   		}
			      	   		model.addAttribute("creditMethod",true);
			      	   		int currentStatus = 1;
			      	   		if(order1.getOrderStatus().equalsIgnoreCase("created")) {
			      	   			currentStatus = 2;
			      	   		}else if (order1.getOrderStatus().equalsIgnoreCase("processing")) {
			      	   			currentStatus = 3;
			      	   		}else if (order1.getOrderStatus().equalsIgnoreCase("shipped")) {
			      	   			currentStatus = 4;
			      	   		}else if (order1.getOrderStatus().equalsIgnoreCase("intransit")) {
			      	   			currentStatus = 5;
			      	   		}else if (order1.getOrderStatus().equalsIgnoreCase("delivered")) {
			      	   			currentStatus = 6;
			      	   		}else {
			      	   			currentStatus = 2;
			      	   		}
			      	   		model.addAttribute("estimatedDeliveryDate",estimatedDeliveryDate);
			      	   		model.addAttribute("order",order1);
			      	   		model.addAttribute("currentStatus",currentStatus);
			      	   		model.addAttribute("cartItemList", order1.getCartItemList());
			      	   		List<OrderLog> orderLogList = orderLogService.findByOrderByOrderByIdDesc(order1);
			      	   		if(orderLogList.isEmpty()) {
			      	   			model.addAttribute("emptyLog",true);
			      	   		}else {
			      	   			model.addAttribute("emptyLog",false);
			      	   			model.addAttribute("orderLogList",orderLogList);
			      	   		}
			        	 return "orderdetails";
		        	 }
		        	 List<Order> ordersList = orderService.searchOrders(keyword);
		        	 if(ordersList == null || ordersList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
		        	 model.addAttribute("ordersList", ordersList);
		        	 return "orderList";
		        	}
		         case "cart" :
		        	 
		        	 ShoppingCart shoppingCart = shoppingCartService.searchCarts(keyword);
		        	 if(shoppingCart == null) {
		        		 return "norecordfound";
		        	}else {
		        	 model.addAttribute("shoppingCart", shoppingCart);
		        	 model.addAttribute("emptyCart",false);	
		        	 return "shoppingCart";
		        	}
		         case "pages" :
		        	 List<StaticPage> pageList = staticPageService.searchPages(keyword);
		        	 if(pageList == null || pageList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
		     		 model.addAttribute("pageList", pageList);
		     		 return "pages";
		        	}
		         case "coupons" :
		        	 List<PromoCodes> promoCodesList = promoCodesService.searchCoupons(keyword);
		        	 if(promoCodesList == null || promoCodesList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
		        	 model.addAttribute("promoCodesList", promoCodesList);
		        	 return "allcoupons";
		        	}
			     case "inquiry" :
			    	 List<Contact> contactList = contactService.searchContacts(keyword);
			    	 if(contactList == null || contactList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
			    	 model.addAttribute("contactList",contactList);
			 		 return "contactList";
		        	}
			     case "subscribers" :
			    	 List<Newsletter> newsletterList = newsletterService.searchSubscribers(keyword);
			    	 if(newsletterList == null || newsletterList.size() == 0) {
		        		 return "norecordfound";
		        	}else {
			    	 model.addAttribute("contactList",newsletterList);
			 		 return "subscriberlists";
		        	}
		         default :
		        	 return "norecordfound";
					}
				}
				return "redirect:/home"; 
    }

}

package com.adminportal.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.Order;
import com.adminportal.domain.OrderLog;
import com.adminportal.domain.User;
import com.adminportal.repository.OrderLogRepository;
import com.adminportal.repository.OrderRepository;
import com.adminportal.service.OrderLogService;
import com.adminportal.service.OrderService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.StripeService;
import com.adminportal.utility.MailConstructor;
import com.stripe.model.Charge;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private StripeService paymentsService;
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
	
	@Autowired
	private OrderLogRepository orderLogRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	@RequestMapping("/orderList")
	public String orderList(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<Order> ordersList = orderService.findAllByOrderDateDesc();
		model.addAttribute("ordersList", ordersList);
		if(ordersList == null) {
			model.addAttribute("emptyOrder", true);
			return "orderList";
		}else {
			model.addAttribute("emptyOrder", false);
		}
		return "orderList";
		
	}
	
	@RequestMapping(value = "/orderdetails/{orderId}")
	   public String orderDetailsPage(@PathVariable Long orderId, Model model,@AuthenticationPrincipal User activeUser) {
			User user = userService.findByUsername(activeUser.getUsername());
	        model.addAttribute("user", user);
	        Charge charge;
	       Order order;
	       try {
	    	   order = orderService.findOne(orderId);
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
	
	@RequestMapping(value = "/takeaction/{orderId}", method = RequestMethod.POST)
	   public String orderTakeActions(@PathVariable Long orderId, String takeAction,String trackingId,String carrier, Model model,@AuthenticationPrincipal User activeUser) {
			User user = userService.findByUsername(activeUser.getUsername());
	        model.addAttribute("user", user);
	        Charge charge;
	       Order order;
	       try {
	    	   order = orderService.findOne(orderId);
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
	   		int currentStatus = 2;
	   		if(takeAction.equalsIgnoreCase("processing")) {
	   			order.setOrderStatus(takeAction);
	   			order.setTrackingNumber(trackingId);
	   			order.setShippingCarrier(carrier);
	   			orderRepository.save(order);
	   			
	   			OrderLog orderLog = new OrderLog();
		   		orderLog.setOrder(order);
		   		orderLog.setUpdatedBy(user.getFirstName()+ " " + user.getLastName());
		   		orderLog.setUpdatedDate(Calendar.getInstance().getTime());
		   		orderLog.setProcessingStatus(takeAction);
		   		orderLog.setUserReason("Order received and processed for shipping");
		   		orderLogRepository.save(orderLog);
		   		currentStatus = 3;
	   		}else if(takeAction.equalsIgnoreCase("shipped")) {
	   			order.setOrderStatus(takeAction);
	   			order.setTrackingNumber(trackingId);
	   			order.setShippingCarrier(carrier);
	   			orderRepository.save(order);
	   			
	   			OrderLog orderLog = new OrderLog();
		   		orderLog.setOrder(order);
		   		orderLog.setUpdatedBy(user.getFirstName()+ " " + user.getLastName());
		   		orderLog.setUpdatedDate(Calendar.getInstance().getTime());
		   		orderLog.setProcessingStatus(takeAction);
		   		orderLog.setUserReason("Order shipped via "+ carrier + " and tracking number "+trackingId);
		   		orderLogRepository.save(orderLog);
		   	//Sending Confirmation Email to customer
			//    mailSender.send(mailConstructor.constructGuestOrderConfirmationEmail(order,Locale.ENGLISH));
		   		currentStatus = 4;
	   		}else if(takeAction.equalsIgnoreCase("intransit")) {
	   			order.setOrderStatus(takeAction);
	   			orderRepository.save(order);
	   			
	   			OrderLog orderLog = new OrderLog();
		   		orderLog.setOrder(order);
		   		orderLog.setUpdatedBy(user.getFirstName()+ " " + user.getLastName());
		   		orderLog.setUpdatedDate(Calendar.getInstance().getTime());
		   		orderLog.setProcessingStatus(takeAction);
		   		orderLog.setUserReason("Order is in transit for delivery via "+order.getShippingCarrier()+" with "+order.getTrackingNumber());
		   		orderLogRepository.save(orderLog);
		   		currentStatus = 5;
	   		}else if(takeAction.equalsIgnoreCase("delivered")) {
	   			order.setOrderStatus(takeAction);
	   			orderRepository.save(order);
	   			
	   			OrderLog orderLog = new OrderLog();
		   		orderLog.setOrder(order);
		   		orderLog.setUpdatedBy(user.getFirstName()+ " " + user.getLastName());
		   		orderLog.setUpdatedDate(Calendar.getInstance().getTime());
		   		orderLog.setProcessingStatus(takeAction);
		   		orderLog.setUserReason("Order delivivered via "+order.getShippingCarrier()+" with "+order.getTrackingNumber());
		   		orderLogRepository.save(orderLog);
		   		currentStatus = 6;
	   		}else {
	   			order.setOrderStatus(takeAction);
	   			orderRepository.save(order);
	   			
	   			OrderLog orderLog = new OrderLog();
		   		orderLog.setOrder(order);
		   		orderLog.setUpdatedBy(user.getFirstName()+ " " + user.getLastName());
		   		orderLog.setUpdatedDate(Calendar.getInstance().getTime());
		   		orderLog.setProcessingStatus(takeAction);
		   		orderLog.setUserReason("Order cancelled");
		   		orderLogRepository.save(orderLog);
		   	//process for refund
	   			currentStatus = 0;
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
	
	
}

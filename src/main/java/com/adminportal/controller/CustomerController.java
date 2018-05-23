package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminportal.domain.User;
import com.adminportal.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/customers")
    public String customerManagement(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);

        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);

        return "customers";
    }
	
	@RequestMapping(value="/customerInfo", method=RequestMethod.GET)
    public String customerDetails(@RequestParam("id") Long id,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User customer = userService.findById(id);
        if(customer == null) {
        	List<User> userList = userService.findAll();
            model.addAttribute("userList", userList);

            return "customers";
        }
        model.addAttribute("customer", customer);

        return "customerdetails";
    }
	
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.GET)
    public String updateCustomer(@RequestParam("id") Long id,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User customer = userService.findById(id);
        model.addAttribute("customer", customer);

        return "editcustomer";
    }
}

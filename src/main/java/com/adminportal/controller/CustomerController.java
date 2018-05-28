package com.adminportal.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminportal.domain.Product;
import com.adminportal.domain.User;
import com.adminportal.service.UserService;
import com.adminportal.utility.CountryConstants;
import com.adminportal.utility.USConstants;
import com.adminportal.utility.SecurityUtility;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/customers")
    public String customerManagement(@RequestParam("type") String type,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        List<User> userList;
        if(type.equalsIgnoreCase("members")) {
        	userList = userService.findByUserType("Customer");
        }else if(type.equalsIgnoreCase("employees")) {
        	userList = userService.findByUserType("Admin");
        }else {
        	userList = userService.findAll();
        }
       
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
        List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		List<String> countryList = CountryConstants.listOfCountryName;
		Collections.sort(countryList);
		model.addAttribute("countryList", countryList);
        return "editcustomer";
    }
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
    public String updateCustomerPost(@ModelAttribute("customer") User customer,BindingResult bindingResult,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User existingCustomer = userService.findById(customer.getId());
        String oldPassword = existingCustomer.getPassword();
        customer.setPassword(oldPassword);
        userService.save(customer);
        model.addAttribute("customer", customer);
        List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		List<String> countryList = CountryConstants.listOfCountryName;
		Collections.sort(countryList);
		model.addAttribute("countryList", countryList);

        model.addAttribute("success", true);
        return "editcustomer";
    }
	
	@RequestMapping(value="/deactivateCustomer", method=RequestMethod.GET)
    public String deactivateCustomer(@RequestParam("id") Long id,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User customer = userService.findById(id);
        if(customer == null) {
        	List<User> userList = userService.findAll();
            model.addAttribute("userList", userList);

            return "customers";
        }
        boolean accountEnabled = customer.isEnabled();
        if(accountEnabled) {
        	customer.setEnabled(false);
        }else {
        	customer.setEnabled(true);
        }
        userService.save(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("success", true);

        return "customerdetails";
    }
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.GET)
    public String updatePassword(@RequestParam("id") Long id,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User customer = userService.findById(id);
        model.addAttribute("customer", customer);
        return "updatepassword";
    }
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
    public String updatePasswordPost(@ModelAttribute("customer") User customer,
    		@ModelAttribute("newPassword") String newPassword,
    		@ModelAttribute("retypePassword") String retypePassword,
    		BindingResult bindingResult,Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        User currentUser = userService.findById(customer.getId());
        
        if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
        	if(newPassword.equals(retypePassword)) {
        		BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
        		currentUser.setPassword(passwordEncoder.encode(newPassword));
        		userService.save(currentUser);
        		model.addAttribute("unmatchedPassword", false);
        		model.addAttribute("emptyPassword", false);
        		model.addAttribute("success", true);
        	}else{
        		model.addAttribute("customer", currentUser);
				model.addAttribute("unmatchedPassword", true);
				model.addAttribute("success", false);
				return "updatepassword";
			}
		}else {
			model.addAttribute("customer", currentUser);
			model.addAttribute("emptyPassword", true);
			model.addAttribute("success", false);
			return "updatepassword";
		}
       
        model.addAttribute("customer", currentUser);
        
        return "updatepassword";
    }
}

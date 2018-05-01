package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

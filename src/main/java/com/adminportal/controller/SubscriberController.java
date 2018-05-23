package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.Newsletter;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.NewsletterService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;

@Controller
public class SubscriberController {

	@Autowired
	private NewsletterService newsletterService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private SiteSettingService siteSettingService;
	
	@RequestMapping("/subscriberlists")
	public String subscriberList(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
		List<Newsletter> contactList = (List<Newsletter>) newsletterService.findAllByOrderByIdDesc();
		//	List<Contact> contactList = (List<Contact>)contactService.findAllByOrderByIdDesc();
		if(contactList == null) {
			model.addAttribute("emptyContact", true);
			return "subscriberlists";
		}else {
			model.addAttribute("emptyContact", false);
		}
		model.addAttribute("user",user);
		model.addAttribute("contactList",contactList);
		return "subscriberlists";
	}
}

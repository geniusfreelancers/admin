package com.adminportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adminportal.domain.Contact;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.StaticPage;
import com.adminportal.domain.User;
import com.adminportal.service.ContactService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.StaticPageService;
import com.adminportal.service.UserService;

@Controller
public class ContactController {
	@Autowired
	private UserService userService;
	@Autowired
	private	ContactService contactService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private StaticPageService staticPageService;
	
	@RequestMapping("/contactlists")
	public String contactLists(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
	//	List<Contact> contactList = (List<Contact>) contactRepository.findAll();
		List<Contact> contactList = (List<Contact>)contactService.findAllByOrderByIdDesc();
		if(contactList == null) {
			model.addAttribute("emptyContact", true);
			return "contactList";
		}else {
			model.addAttribute("emptyContact", false);
		}
		model.addAttribute("user",user);
		model.addAttribute("contactList",contactList);
		return "contactList";
	}
	
	@RequestMapping("/contactdetails/{id}")
	public String contactDetails(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser) {
		Contact contact = contactService.findById(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        
        model.addAttribute("contact",contact);
        return "contactdetails";
	}
}

package com.adminportal.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.HomePage;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.HomePageService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;
import com.adminportal.utility.USConstants;

@Controller
public class SiteSettingsController {

	@Autowired
	private UserService userService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private HomePageService homePageService;
	
	@RequestMapping("/settings")
	public String settings(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
		List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList",stateList);
        model.addAttribute("user", user);
		return "settings";
	}
	
	
	@RequestMapping(value="/settings/update", method=RequestMethod.POST)
	public String settingsPOST(@ModelAttribute("siteSettings") SiteSetting siteSettings,
			Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		
		siteSettingService.save(siteSettings);
		model.addAttribute("siteSettings",siteSettings);
		List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList",stateList);
        model.addAttribute("user", user);
		return "redirect:/settings";
	}
	
	@RequestMapping("/homesettings")
	public String homeSettings(Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
		HomePage homePage = homePageService.findOne(new Long(1));
		if(homePage == null) {
			homePage = new HomePage();
		}
		model.addAttribute("homePage",homePage);
		List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList",stateList);
        model.addAttribute("user", user);
		return "homesettings";	
	}
}

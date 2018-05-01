package com.adminportal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adminportal.domain.PromoCodes;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.repository.PromoCodesRepository;
import com.adminportal.service.PromoCodesService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;
@Controller
@RequestMapping("/coupons")
public class CouponController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SiteSettingService siteSettingService;
	
	@Autowired
	private PromoCodesService promoCodesService;
	
	@Autowired
	private PromoCodesRepository promoCodesRepository;
	
	@RequestMapping("/allcoupons/{type}")
	public String coupons(@PathVariable String type, Model model,@AuthenticationPrincipal User activeUser) {
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		List<PromoCodes> promoCodesList = promoCodesService.findAll();
		if(promoCodesList == null) {
			model.addAttribute("emptyPage", true);
		}else {
		
		if(type.equalsIgnoreCase("active")) {
			List<PromoCodes>  newpromoCodesList = new ArrayList<PromoCodes>();
			
			for (PromoCodes promoCodes : promoCodesList) {
				if(promoCodes.isPromoStatus()) {
					newpromoCodesList.add(promoCodes);
				}
				if(newpromoCodesList == null) {
					model.addAttribute("emptyPage", true);
				}else {
					model.addAttribute("emptyPage", false);
					model.addAttribute("promoCodesList", newpromoCodesList);
				}
			}
		}else if(type.equalsIgnoreCase("inactive")) {
			List<PromoCodes>  newpromoCodesList = new ArrayList<PromoCodes>();
			
			for (PromoCodes promoCodes : promoCodesList) {
				if(promoCodes.isPromoStatus() == false) {
					newpromoCodesList.add(promoCodes);
				}
				if(newpromoCodesList == null) {
					model.addAttribute("emptyPage", true);
				}else {
					model.addAttribute("emptyPage", false);
					model.addAttribute("promoCodesList", newpromoCodesList);
				}
			}
		}else {
			model.addAttribute("promoCodesList", promoCodesList);
			
			model.addAttribute("emptyPage", false);
		}}
		return "allcoupons";
	}
	
	
	@RequestMapping("/addcoupon")
	public String addCoupon(Model model,@AuthenticationPrincipal User activeUser)
	{
		PromoCodes promoCodes = new PromoCodes();
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("promoCodes", promoCodes);
		return "addcoupon";
	}
	
	@RequestMapping(value="/addcoupon", method=RequestMethod.POST)
	public String addCouponPOST(@ModelAttribute("promoCodes") PromoCodes promoCodes,BindingResult bindingResult, Model model,@AuthenticationPrincipal User activeUser)
	{
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		//Change coupon code to lowercase to compare
		if(promoCodesService.findByPromoCode(promoCodes.getCouponCode()) != null) {
			model.addAttribute("startBefore", false);
			model.addAttribute("samedate", false);
			model.addAttribute("duplicatepromo", true);
			model.addAttribute("promoCodes", promoCodes);
			PromoCodes existingpromo =promoCodesService.findByPromoCode(promoCodes.getCouponCode());
			model.addAttribute("existingpromo", existingpromo);
			return "addcoupon";
		}
		if(promoCodes.getStartDate().after(promoCodes.getExpiryDate())) {
			model.addAttribute("promoCodes", promoCodes);
			model.addAttribute("duplicatepromo", false);
			model.addAttribute("samedate", false);
			model.addAttribute("startBefore", true);
			return "addcoupon";
		}
		if(promoCodes.getStartDate().equals(promoCodes.getExpiryDate())) {
			model.addAttribute("promoCodes", promoCodes);
			model.addAttribute("duplicatepromo", false);
			model.addAttribute("startBefore", false);
			model.addAttribute("samedate", true);
			return "addcoupon";
		}
		
		promoCodes.setCouponCode(promoCodes.getCouponCode().toLowerCase());
		promoCodes.setPromoType(promoCodes.getPromoType().toLowerCase());
		/*SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date startDate = formatter.parse(promoCodes.getStartDate().toString()); 
		Date endDate = formatter.parse(promoCodes.getExpiryDate().toString()); */
		promoCodes.setStartDate(promoCodes.getStartDate());
		promoCodes.setExpiryDate(promoCodes.getExpiryDate());
        promoCodes.setAddedBy(user.getFirstName()+" "+user.getLastName());
        promoCodes.setAddedOn(Calendar.getInstance().getTime());
        promoCodesRepository.save(promoCodes);
        model.addAttribute("promoCodes", promoCodes);
        model.addAttribute("duplicatepromo", false);
		return "redirect:/coupons/allcoupons/all";
	}
	
	@RequestMapping("/details/{id}")
	public String couponDetails(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser)
	{
		PromoCodes promoCodes = promoCodesService.findOne(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
		if(promoCodes != null) {
			model.addAttribute("promoCodes", promoCodes);
			return "promodetails";
		}else {
			return "redirect:/coupons/allcoupons/all";
		}
	}
	
	@RequestMapping("/active/{id}")
	public String updateCouponStatus(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser,HttpServletRequest request) {
		PromoCodes promoCodes = promoCodesService.findOne(id);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        if(promoCodes.isPromoStatus()) {
        	promoCodes.setPromoStatus(false);
        }else {
        	promoCodes.setPromoStatus(true);
        }
        promoCodes.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
        promoCodes.setUpdatedOn(Calendar.getInstance().getTime());
        promoCodesRepository.save(promoCodes); 
        if(request.getHeader("referer")!= null) {
            String referrer = request.getHeader("referer");
            String newref = referrer.substring(referrer.length()-6,referrer.length());
    		if(newref.equalsIgnoreCase("active")){
            	return "redirect:/coupons/allcoupons/active";
            }
            }
        return "redirect:/coupons/allcoupons/all";
	}
	

	@RequestMapping("/edit/{id}")
	public String editCoupon(@PathVariable Long id, Model model,@AuthenticationPrincipal User activeUser) {
		PromoCodes promoCodes = promoCodesService.findOne(id);
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
        model.addAttribute("siteSettings",siteSettings);
        User user = userService.findByUsername(activeUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("promoCodes",promoCodes);
        return "editpromo";
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editCouponPost(
			@Valid @ModelAttribute("promoCodes") PromoCodes promoCodes, BindingResult result,Model model,
			HttpServletRequest request, @AuthenticationPrincipal User activeUser) {
		 User user = userService.findByUsername(activeUser.getUsername());
	     model.addAttribute("user", user);
	     //PromoCodes newPromoCodes = promoCodesService.findOne(promoCodes.getId());
	     // problem with Added bY and Added on is getting as null nee to check
	     promoCodes.setAddedBy(promoCodes.getAddedBy());
	     promoCodes.setAddedOn(promoCodes.getAddedOn());
	     promoCodes.setStartDate(promoCodes.getStartDate());
	     promoCodes.setExpiryDate( promoCodes.getExpiryDate());
	     promoCodes.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
	     promoCodes.setUpdatedOn(Calendar.getInstance().getTime());
	     promoCodesRepository.save(promoCodes); 
	     return "redirect:/coupons/allcoupons";
	}
	
	@RequestMapping(value="/allcoupons/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			){
		//productService.removeOne(Long.parseLong(id.substring(11)));
		promoCodesService.removeOne(Long.parseLong(id.substring(11)));
		List<PromoCodes> productList = promoCodesService.findAll();
		
		model.addAttribute("productList", productList);
		
		return "redirect:/product/productList";
		
	}
	
}

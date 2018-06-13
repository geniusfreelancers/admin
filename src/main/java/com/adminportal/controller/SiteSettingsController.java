package com.adminportal.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.HomePage;
import com.adminportal.domain.HomePageAdditional;
import com.adminportal.domain.SiteSetting;
import com.adminportal.domain.User;
import com.adminportal.service.HomePageService;
import com.adminportal.service.SiteSettingService;
import com.adminportal.service.UserService;
import com.adminportal.service.impl.AmazonClient;
import com.adminportal.utility.USConstants;

@Controller
public class SiteSettingsController {
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
    SiteSettingsController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
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
        String fileUrl = endpointUrl + "/" + bucketName + "/";
		model.addAttribute("fileUrl", fileUrl);
		return "settings";
	}
	
	
	@RequestMapping(value="/settings/update", method=RequestMethod.POST)
	public String settingsPOST(@ModelAttribute("siteSettings") SiteSetting siteSettings,BindingResult result,
			Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		MultipartFile siteLogoImage = siteSettings.getSiteLogoImage();
		String siteLogo = siteSettings.getSiteLogo();
		if(siteLogoImage == null || siteLogoImage.isEmpty()) {
			siteSettings.setSiteLogo(siteLogo);
		}else {
			amazonClient.deleteFileFromS3BucketByFilename(siteLogo);
			siteLogo = amazonClient.uploadFile(siteLogoImage);
			siteSettings.setSiteLogo(siteLogo);
		}
		MultipartFile shopOfferBannerImage = siteSettings.getShopOfferBannerImage();
		String shopOfferBanner = siteSettings.getShopOfferBanner();
		if(shopOfferBannerImage == null || shopOfferBannerImage.isEmpty()) {
			siteSettings.setShopOfferBanner(shopOfferBanner);
		}else {
			amazonClient.deleteFileFromS3BucketByFilename(shopOfferBanner);
			shopOfferBanner = amazonClient.uploadFile(shopOfferBannerImage);
			siteSettings.setShopOfferBanner(shopOfferBanner);
		}
		MultipartFile cartOfferBannerImage = siteSettings.getCartOfferBannerImage();
		String cartOfferBanner = siteSettings.getCartOfferBanner();
		if(cartOfferBannerImage == null || cartOfferBannerImage.isEmpty()) {
			siteSettings.setCartOfferBanner(cartOfferBanner);
		}else {
			amazonClient.deleteFileFromS3BucketByFilename(cartOfferBanner);
			cartOfferBanner = amazonClient.uploadFile(cartOfferBannerImage);
			siteSettings.setCartOfferBanner(cartOfferBanner);
		}
		siteSettingService.save(siteSettings);
		
		model.addAttribute("siteSettings",siteSettings);
		List<String> stateList = USConstants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList",stateList);
        model.addAttribute("user", user);
        model.addAttribute("saved",true);
        String fileUrl = endpointUrl + "/" + bucketName + "/";
		model.addAttribute("fileUrl", fileUrl);
		return "settings";
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
		HomePageAdditional homePageAdditional = homePageService.findAdditionalHomePage(new Long(1));
		if(homePageAdditional == null) {
			homePageAdditional = new HomePageAdditional();
		}
		model.addAttribute("homePage",homePage);
		model.addAttribute("homePageAdditional",homePageAdditional);
        model.addAttribute("user", user);
        String fileUrl = endpointUrl + "/" + bucketName + "/";
		model.addAttribute("fileUrl", fileUrl);
		return "homesettings";	
	}
	
	@RequestMapping(value="/homesettings/update", method=RequestMethod.POST)
	public String homeSettingsPOST(@ModelAttribute("homePage") HomePage homePage,
			@ModelAttribute("homePageAdditional") HomePageAdditional homePageAdditional,BindingResult result,
			Model model,@AuthenticationPrincipal User activeUser){
		User user = userService.findByUsername(activeUser.getUsername());
		SiteSetting siteSettings = siteSettingService.findOne(new Long(1));
		model.addAttribute("siteSettings",siteSettings);
		//Slides
		MultipartFile slideOneImage = homePage.getSlideOneImage();
		String slideOneImg = homePage.getSlideOneImg();
		slideOneImg = homePageService.updateImage(slideOneImg, slideOneImage);		
		homePage.setSlideOneImg(slideOneImg);
		
		MultipartFile slideTwoImage = homePage.getSlideTwoImage();
		String slideTwoImg = homePage.getSlideTwoImg();
		slideTwoImg = homePageService.updateImage(slideTwoImg, slideTwoImage);		
		homePage.setSlideTwoImg(slideTwoImg);
		
		MultipartFile slideThreeImage = homePage.getSlideThreeImage();
		String slideThreeImg = homePage.getSlideThreeImg();
		slideThreeImg = homePageService.updateImage(slideThreeImg, slideThreeImage);		
		homePage.setSlideThreeImg(slideThreeImg);
		//Special Banner
		MultipartFile specialOneImage = homePageAdditional.getSpecialOneImage();
		String specialOneImg = homePageAdditional.getSpecialOneImg();
		specialOneImg = homePageService.updateImage(specialOneImg, specialOneImage);		
		homePageAdditional.setSpecialOneImg(specialOneImg);
		
		MultipartFile specialTwoImage = homePageAdditional.getSpecialTwoImage();
		String specialTwoImg = homePageAdditional.getSpecialTwoImg();
		specialTwoImg = homePageService.updateImage(specialTwoImg, specialTwoImage);		
		homePageAdditional.setSpecialTwoImg(specialTwoImg);
		
		MultipartFile specialThreeImage = homePageAdditional.getSpecialThreeImage();
		String specialThreeImg = homePageAdditional.getSpecialThreeImg();
		specialThreeImg = homePageService.updateImage(specialThreeImg, specialThreeImage);		
		homePageAdditional.setSpecialThreeImg(specialThreeImg);
		
		MultipartFile specialFourImage = homePageAdditional.getSpecialFourImage();
		String specialFourImg = homePageAdditional.getSpecialFourImg();
		specialFourImg = homePageService.updateImage(specialFourImg, specialFourImage);		
		homePageAdditional.setSpecialFourImg(specialFourImg);
		
		MultipartFile specialSixImage = homePageAdditional.getSpecialSixImage();
		String specialSixImg = homePageAdditional.getSpecialSixImg();
		specialSixImg = homePageService.updateImage(specialSixImg, specialSixImage);		
		homePageAdditional.setSpecialSixImg(specialSixImg);
		//Special Discount
		MultipartFile bannerOneImage = homePageAdditional.getBannerOneImage();
		String bannerOneImg = homePageAdditional.getBannerOneImg();
		bannerOneImg = homePageService.updateImage(bannerOneImg, bannerOneImage);		
		homePageAdditional.setBannerOneImg(bannerOneImg);
		
		MultipartFile bannerTwoImage = homePageAdditional.getBannerTwoImage();
		String bannerTwoImg = homePageAdditional.getBannerTwoImg();
		bannerTwoImg = homePageService.updateImage(bannerTwoImg, bannerTwoImage);		
		homePageAdditional.setBannerTwoImg(bannerTwoImg);
		
		MultipartFile bannerThreeImage = homePageAdditional.getBannerThreeImage();
		String bannerThreeImg = homePageAdditional.getBannerThreeImg();
		bannerThreeImg = homePageService.updateImage(bannerThreeImg, bannerThreeImage);		
		homePageAdditional.setBannerThreeImg(bannerThreeImg);
		
		MultipartFile bannerFourImage = homePageAdditional.getBannerFourImage();
		String bannerFourImg = homePageAdditional.getBannerFourImg();
		bannerFourImg = homePageService.updateImage(bannerFourImg, bannerFourImage);		
		homePageAdditional.setBannerFourImg(bannerFourImg);
		//Testimonial Background
		MultipartFile testimonialImage = homePageAdditional.getTestimonialImage();
		String testimonialImg = homePageAdditional.getTestimonialImg();
		testimonialImg = homePageService.updateImage(testimonialImg, testimonialImage);		
		homePageAdditional.setTestimonialImg(testimonialImg);
		
		//Promotion
		MultipartFile promotionOneImage = homePage.getPromotionOneImage();
		String promotionOneImg = homePage.getPromotionOneImg();
		promotionOneImg = homePageService.updateImage(promotionOneImg, promotionOneImage);		
		homePage.setPromotionOneImg(promotionOneImg);
				
		MultipartFile promotionTwoImage = homePage.getPromotionTwoImage();
		String promotionTwoImg = homePage.getPromotionTwoImg();
		promotionTwoImg = homePageService.updateImage(promotionTwoImg, promotionTwoImage);		
		homePage.setPromotionTwoImg(promotionTwoImg);
		
		//Category
		MultipartFile categoryOneImage = homePage.getCategoryOneImage();
		String categoryOneImg = homePage.getCategoryOneImg();
		categoryOneImg = homePageService.updateImage(categoryOneImg, categoryOneImage);		
		homePage.setCategoryOneImg(categoryOneImg);
		
		MultipartFile categoryTwoImage = homePage.getCategoryTwoImage();
		String categoryTwoImg = homePage.getCategoryTwoImg();
		categoryTwoImg = homePageService.updateImage(categoryTwoImg, categoryTwoImage);		
		homePage.setCategoryTwoImg(categoryTwoImg);
		
		MultipartFile categoryThreeImage = homePage.getCategoryThreeImage();
		String categoryThreeImg = homePage.getCategoryThreeImg();
		categoryThreeImg = homePageService.updateImage(categoryThreeImg, categoryThreeImage);		
		homePage.setCategoryThreeImg(categoryThreeImg);
		
		MultipartFile categoryFourImage = homePage.getCategoryFourImage();
		String categoryFourImg = homePage.getCategoryFourImg();
		categoryFourImg = homePageService.updateImage(categoryFourImg, categoryFourImage);		
		homePage.setCategoryFourImg(categoryFourImg);
		//Offer
		MultipartFile offerOneImage = homePage.getOfferOneImage();
		String offerOneImg = homePage.getOfferOneImg();
		offerOneImg = homePageService.updateImage(offerOneImg, offerOneImage);		
		homePage.setOfferOneImg(offerOneImg);
		
		MultipartFile offerTwoImage = homePage.getOfferTwoImage();
		String offerTwoImg = homePage.getOfferTwoImg();
		offerTwoImg = homePageService.updateImage(offerTwoImg, offerTwoImage);		
		homePage.setOfferTwoImg(offerTwoImg);
		
		MultipartFile offerThreeImage = homePage.getOfferThreeImage();
		String offerThreeImg = homePage.getOfferThreeImg();
		offerThreeImg = homePageService.updateImage(offerThreeImg, offerThreeImage);		
		homePage.setOfferThreeImg(offerThreeImg);
		
		MultipartFile offerFourImage = homePage.getOfferFourImage();
		String offerFourImg = homePage.getOfferFourImg();
		offerFourImg = homePageService.updateImage(offerFourImg, offerFourImage);		
		homePage.setOfferFourImg(offerFourImg);
		
		homePageService.save(homePage);
		homePageService.saveAdditionalHomePage(homePageAdditional);
		model.addAttribute("homePage",homePage);
		model.addAttribute("homePageAdditional",homePageAdditional);
        model.addAttribute("user", user);
        model.addAttribute("saved",true);
        String fileUrl = endpointUrl + "/" + bucketName + "/";
		model.addAttribute("fileUrl", fileUrl);
		return "homesettings";
	}
}

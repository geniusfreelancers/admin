package com.adminportal.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class SiteSetting {
	@Id
	private Long id =(long) 1;
	private BigDecimal freeShippingMin;
	
	private BigDecimal shippingCost;
	private BigDecimal premiumShippingCost;
	private String siteLogo;
	
	@Transient
	private MultipartFile siteLogoImage;
	private String shopOfferBanner;
	@Transient
	private MultipartFile shopOfferBannerImage;
	private String cartOfferBanner;
	@Transient
	private MultipartFile cartOfferBannerImage;
	private String storeAddress1;
	private String storeAddress2;
	private String storeAddressCity;
	private String storeAddressState;
	private String storeAddressZip;
	private String storePhone;
	private String storeFax;
	private String storeSupportEmail;
	private String storeInfoEmail;

	private boolean loginKillSwitch;
	
	private String facebookUrl;
	private String twitterUrl;
	private String instagramUrl;
	private String googleUrl;
	private String pintrestUrl;
	
	private String topOfferTicker;
	
	private String bottomOfferTickerLeft;
	private String bottomOfferTickerLeftUrl;
	private String bottomOfferTickerCenter;
	private String bottomOfferTickerCenterUrl;
	private String bottomOfferTickerRight;
	private String bottomOfferTickerRightUrl;
	
	public MultipartFile getShopOfferBannerImage() {
		return shopOfferBannerImage;
	}
	public void setShopOfferBannerImage(MultipartFile shopOfferBannerImage) {
		this.shopOfferBannerImage = shopOfferBannerImage;
	}
	public MultipartFile getCartOfferBannerImage() {
		return cartOfferBannerImage;
	}
	public void setCartOfferBannerImage(MultipartFile cartOfferBannerImage) {
		this.cartOfferBannerImage = cartOfferBannerImage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getFreeShippingMin() {
		return freeShippingMin;
	}
	public void setFreeShippingMin(BigDecimal freeShippingMin) {
		this.freeShippingMin = freeShippingMin;
	}
	public BigDecimal getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public BigDecimal getPremiumShippingCost() {
		return premiumShippingCost;
	}
	public void setPremiumShippingCost(BigDecimal premiumShippingCost) {
		this.premiumShippingCost = premiumShippingCost;
	}
	
	public String getSiteLogo() {
		return siteLogo;
	}
	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}

	public MultipartFile getSiteLogoImage() {
		return siteLogoImage;
	}
	public void setSiteLogoImage(MultipartFile siteLogoImage) {
		this.siteLogoImage = siteLogoImage;
	}
	public String getShopOfferBanner() {
		return shopOfferBanner;
	}
	public void setShopOfferBanner(String shopOfferBanner) {
		this.shopOfferBanner = shopOfferBanner;
	}
	public String getCartOfferBanner() {
		return cartOfferBanner;
	}
	public void setCartOfferBanner(String cartOfferBanner) {
		this.cartOfferBanner = cartOfferBanner;
	}
	public String getTopOfferTicker() {
		return topOfferTicker;
	}
	public void setTopOfferTicker(String topOfferTicker) {
		this.topOfferTicker = topOfferTicker;
	}
	public String getBottomOfferTickerLeft() {
		return bottomOfferTickerLeft;
	}
	public void setBottomOfferTickerLeft(String bottomOfferTickerLeft) {
		this.bottomOfferTickerLeft = bottomOfferTickerLeft;
	}
	public String getBottomOfferTickerLeftUrl() {
		return bottomOfferTickerLeftUrl;
	}
	public void setBottomOfferTickerLeftUrl(String bottomOfferTickerLeftUrl) {
		this.bottomOfferTickerLeftUrl = bottomOfferTickerLeftUrl;
	}
	public String getBottomOfferTickerCenter() {
		return bottomOfferTickerCenter;
	}
	public void setBottomOfferTickerCenter(String bottomOfferTickerCenter) {
		this.bottomOfferTickerCenter = bottomOfferTickerCenter;
	}
	public String getBottomOfferTickerCenterUrl() {
		return bottomOfferTickerCenterUrl;
	}
	public void setBottomOfferTickerCenterUrl(String bottomOfferTickerCenterUrl) {
		this.bottomOfferTickerCenterUrl = bottomOfferTickerCenterUrl;
	}
	public String getBottomOfferTickerRight() {
		return bottomOfferTickerRight;
	}
	public void setBottomOfferTickerRight(String bottomOfferTickerRight) {
		this.bottomOfferTickerRight = bottomOfferTickerRight;
	}
	public String getBottomOfferTickerRightUrl() {
		return bottomOfferTickerRightUrl;
	}
	public void setBottomOfferTickerRightUrl(String bottomOfferTickerRightUrl) {
		this.bottomOfferTickerRightUrl = bottomOfferTickerRightUrl;
	}
	public String getStoreAddress1() {
		return storeAddress1;
	}
	public void setStoreAddress1(String storeAddress1) {
		this.storeAddress1 = storeAddress1;
	}
	public String getStoreAddress2() {
		return storeAddress2;
	}
	public void setStoreAddress2(String storeAddress2) {
		this.storeAddress2 = storeAddress2;
	}
	public String getStoreAddressCity() {
		return storeAddressCity;
	}
	public void setStoreAddressCity(String storeAddressCity) {
		this.storeAddressCity = storeAddressCity;
	}
	public String getStoreAddressState() {
		return storeAddressState;
	}
	public void setStoreAddressState(String storeAddressState) {
		this.storeAddressState = storeAddressState;
	}
	public String getStoreAddressZip() {
		return storeAddressZip;
	}
	public void setStoreAddressZip(String storeAddressZip) {
		this.storeAddressZip = storeAddressZip;
	}
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public String getStoreFax() {
		return storeFax;
	}
	public void setStoreFax(String storeFax) {
		this.storeFax = storeFax;
	}
	public String getStoreSupportEmail() {
		return storeSupportEmail;
	}
	public void setStoreSupportEmail(String storeSupportEmail) {
		this.storeSupportEmail = storeSupportEmail;
	}
	public String getStoreInfoEmail() {
		return storeInfoEmail;
	}
	public void setStoreInfoEmail(String storeInfoEmail) {
		this.storeInfoEmail = storeInfoEmail;
	}
	public boolean isLoginKillSwitch() {
		return loginKillSwitch;
	}
	public void setLoginKillSwitch(boolean loginKillSwitch) {
		this.loginKillSwitch = loginKillSwitch;
	}
	public String getFacebookUrl() {
		return facebookUrl;
	}
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	public String getTwitterUrl() {
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	public String getInstagramUrl() {
		return instagramUrl;
	}
	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}
	public String getGoogleUrl() {
		return googleUrl;
	}
	public void setGoogleUrl(String googleUrl) {
		this.googleUrl = googleUrl;
	}
	public String getPintrestUrl() {
		return pintrestUrl;
	}
	public void setPintrestUrl(String pintrestUrl) {
		this.pintrestUrl = pintrestUrl;
	}	
	
}

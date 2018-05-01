package com.adminportal.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiteSetting {
	@Id
	private Long id =(long) 1;
	private BigDecimal freeShippingMin;
	
	private BigDecimal shippingCost;
	private String homeSlide;
	private BigDecimal premiumShippingCost;
	private String siteLogo;
	private String categoryBanner;
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
	public String getHomeSlide() {
		return homeSlide;
	}
	public void setHomeSlide(String homeSlide) {
		this.homeSlide = homeSlide;
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
	public String getCategoryBanner() {
		return categoryBanner;
	}
	public void setCategoryBanner(String categoryBanner) {
		this.categoryBanner = categoryBanner;
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
	
}

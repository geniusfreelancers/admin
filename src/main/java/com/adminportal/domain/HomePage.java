package com.adminportal.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class HomePage {
	@Id
	private Long id =(long) 1;
	private String slideOneImg;
	private String slideOneUrl;
	private String slideTwoImg;
	private String slideTwoUrl;
	private String slideThreeImg;
	private String slideThreeUrl;
	private String categoryOneImg;
	private String categoryOneUrl;
	private String categoryOneLable;
	private String categoryTwoImg;
	private String categoryTwoUrl;
	private String categoryTwoLable;
	private String categoryThreeImg;
	private String categoryThreeUrl;
	private String categoryThreeLable;
	private String categoryFourImg;
	private String categoryFourUrl;
	private String categoryFourLable;
	private String bannerOneImg;
	private String bannerOneUrl;
	private String bannerTwoImg;
	private String bannerTwoUrl;
	private String bannerThreeImg;
	private String bannerThreeUrl;
	private String bannerFourImg;
	private String bannerFourUrl;
	private String bannerFiveImg;
	private String bannerFiveUrl;
	private String bannerSixImg;
	private String bannerSixUrl;
	private String offerOneImg;
	private String offerOneUrl;
	private String offerTwoImg;
	private String offerTwoUrl;
	private String offerThreeImg;
	private String offerThreeUrl;
	private String offerFourImg;
	private String offerFourUrl;
	private Date updatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSlideOneImg() {
		return slideOneImg;
	}
	public void setSlideOneImg(String slideOneImg) {
		this.slideOneImg = slideOneImg;
	}
	public String getSlideOneUrl() {
		return slideOneUrl;
	}
	public void setSlideOneUrl(String slideOneUrl) {
		this.slideOneUrl = slideOneUrl;
	}
	public String getSlideTwoImg() {
		return slideTwoImg;
	}
	public void setSlideTwoImg(String slideTwoImg) {
		this.slideTwoImg = slideTwoImg;
	}
	public String getSlideTwoUrl() {
		return slideTwoUrl;
	}
	public void setSlideTwoUrl(String slideTwoUrl) {
		this.slideTwoUrl = slideTwoUrl;
	}
	public String getSlideThreeImg() {
		return slideThreeImg;
	}
	public void setSlideThreeImg(String slideThreeImg) {
		this.slideThreeImg = slideThreeImg;
	}
	public String getSlideThreeUrl() {
		return slideThreeUrl;
	}
	public void setSlideThreeUrl(String slideThreeUrl) {
		this.slideThreeUrl = slideThreeUrl;
	}
	public String getCategoryOneImg() {
		return categoryOneImg;
	}
	public void setCategoryOneImg(String categoryOneImg) {
		this.categoryOneImg = categoryOneImg;
	}
	public String getCategoryOneUrl() {
		return categoryOneUrl;
	}
	public void setCategoryOneUrl(String categoryOneUrl) {
		this.categoryOneUrl = categoryOneUrl;
	}
	public String getCategoryOneLable() {
		return categoryOneLable;
	}
	public void setCategoryOneLable(String categoryOneLable) {
		this.categoryOneLable = categoryOneLable;
	}
	public String getCategoryTwoImg() {
		return categoryTwoImg;
	}
	public void setCategoryTwoImg(String categoryTwoImg) {
		this.categoryTwoImg = categoryTwoImg;
	}
	public String getCategoryTwoUrl() {
		return categoryTwoUrl;
	}
	public void setCategoryTwoUrl(String categoryTwoUrl) {
		this.categoryTwoUrl = categoryTwoUrl;
	}
	public String getCategoryTwoLable() {
		return categoryTwoLable;
	}
	public void setCategoryTwoLable(String categoryTwoLable) {
		this.categoryTwoLable = categoryTwoLable;
	}
	public String getCategoryThreeImg() {
		return categoryThreeImg;
	}
	public void setCategoryThreeImg(String categoryThreeImg) {
		this.categoryThreeImg = categoryThreeImg;
	}
	public String getCategoryThreeUrl() {
		return categoryThreeUrl;
	}
	public void setCategoryThreeUrl(String categoryThreeUrl) {
		this.categoryThreeUrl = categoryThreeUrl;
	}
	public String getCategoryThreeLable() {
		return categoryThreeLable;
	}
	public void setCategoryThreeLable(String categoryThreeLable) {
		this.categoryThreeLable = categoryThreeLable;
	}
	public String getCategoryFourImg() {
		return categoryFourImg;
	}
	public void setCategoryFourImg(String categoryFourImg) {
		this.categoryFourImg = categoryFourImg;
	}
	public String getCategoryFourUrl() {
		return categoryFourUrl;
	}
	public void setCategoryFourUrl(String categoryFourUrl) {
		this.categoryFourUrl = categoryFourUrl;
	}
	public String getCategoryFourLable() {
		return categoryFourLable;
	}
	public void setCategoryFourLable(String categoryFourLable) {
		this.categoryFourLable = categoryFourLable;
	}
	public String getBannerOneImg() {
		return bannerOneImg;
	}
	public void setBannerOneImg(String bannerOneImg) {
		this.bannerOneImg = bannerOneImg;
	}
	public String getBannerOneUrl() {
		return bannerOneUrl;
	}
	public void setBannerOneUrl(String bannerOneUrl) {
		this.bannerOneUrl = bannerOneUrl;
	}
	public String getBannerTwoImg() {
		return bannerTwoImg;
	}
	public void setBannerTwoImg(String bannerTwoImg) {
		this.bannerTwoImg = bannerTwoImg;
	}
	public String getBannerTwoUrl() {
		return bannerTwoUrl;
	}
	public void setBannerTwoUrl(String bannerTwoUrl) {
		this.bannerTwoUrl = bannerTwoUrl;
	}
	public String getBannerThreeImg() {
		return bannerThreeImg;
	}
	public void setBannerThreeImg(String bannerThreeImg) {
		this.bannerThreeImg = bannerThreeImg;
	}
	public String getBannerThreeUrl() {
		return bannerThreeUrl;
	}
	public void setBannerThreeUrl(String bannerThreeUrl) {
		this.bannerThreeUrl = bannerThreeUrl;
	}
	public String getBannerFourImg() {
		return bannerFourImg;
	}
	public void setBannerFourImg(String bannerFourImg) {
		this.bannerFourImg = bannerFourImg;
	}
	public String getBannerFourUrl() {
		return bannerFourUrl;
	}
	public void setBannerFourUrl(String bannerFourUrl) {
		this.bannerFourUrl = bannerFourUrl;
	}
	public String getBannerFiveImg() {
		return bannerFiveImg;
	}
	public void setBannerFiveImg(String bannerFiveImg) {
		this.bannerFiveImg = bannerFiveImg;
	}
	public String getBannerFiveUrl() {
		return bannerFiveUrl;
	}
	public void setBannerFiveUrl(String bannerFiveUrl) {
		this.bannerFiveUrl = bannerFiveUrl;
	}
	public String getBannerSixImg() {
		return bannerSixImg;
	}
	public void setBannerSixImg(String bannerSixImg) {
		this.bannerSixImg = bannerSixImg;
	}
	public String getBannerSixUrl() {
		return bannerSixUrl;
	}
	public void setBannerSixUrl(String bannerSixUrl) {
		this.bannerSixUrl = bannerSixUrl;
	}
	public String getOfferOneImg() {
		return offerOneImg;
	}
	public void setOfferOneImg(String offerOneImg) {
		this.offerOneImg = offerOneImg;
	}
	public String getOfferOneUrl() {
		return offerOneUrl;
	}
	public void setOfferOneUrl(String offerOneUrl) {
		this.offerOneUrl = offerOneUrl;
	}
	public String getOfferTwoImg() {
		return offerTwoImg;
	}
	public void setOfferTwoImg(String offerTwoImg) {
		this.offerTwoImg = offerTwoImg;
	}
	public String getOfferTwoUrl() {
		return offerTwoUrl;
	}
	public void setOfferTwoUrl(String offerTwoUrl) {
		this.offerTwoUrl = offerTwoUrl;
	}
	public String getOfferThreeImg() {
		return offerThreeImg;
	}
	public void setOfferThreeImg(String offerThreeImg) {
		this.offerThreeImg = offerThreeImg;
	}
	public String getOfferThreeUrl() {
		return offerThreeUrl;
	}
	public void setOfferThreeUrl(String offerThreeUrl) {
		this.offerThreeUrl = offerThreeUrl;
	}
	public String getOfferFourImg() {
		return offerFourImg;
	}
	public void setOfferFourImg(String offerFourImg) {
		this.offerFourImg = offerFourImg;
	}
	public String getOfferFourUrl() {
		return offerFourUrl;
	}
	public void setOfferFourUrl(String offerFourUrl) {
		this.offerFourUrl = offerFourUrl;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}

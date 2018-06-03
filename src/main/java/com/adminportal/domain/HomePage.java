package com.adminportal.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;


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
	//Main Category
	private String categoryOneImg;
	private String categoryOneUrl;
	private String categoryOneLable;
	private String categoryOneLable2;
	private String categoryTwoImg;
	private String categoryTwoUrl;
	private String categoryTwoLable;
	private String categoryTwoLable2;
	private String categoryThreeImg;
	private String categoryThreeUrl;
	private String categoryThreeLable;
	private String categoryThreeLable2;
	private String categoryFourImg;
	private String categoryFourUrl;
	private String categoryFourLable;
	private String categoryFourLable2;
	
	//Promotion
	private String promotionOneImg;
	private String promotionOneUrl;
	private String promotionOneLable;
	private String promotionOneText;
	
	private String promotionTwoImg;
	private String promotionTwoUrl;
	private String promotionTwoLable;
	private String promotionTwoText;
	//Offer
	private String offerOneImg;
	private String offerOneTitle;
	private String offerOneText;
	private String offerOneUrl;
	private String offerTwoImg;
	private String offerTwoTitle;
	private String offerTwoText;
	private String offerTwoUrl;
	private String offerThreeImg;
	private String offerThreeTitle;
	private String offerThreeText;
	private String offerThreeUrl;
	private String offerFourImg;
	private String offerFourTitle;
	private String offerFourText;
	private String offerFourUrl;
	
	private Date updatedDate;
	@Transient
	private MultipartFile slideOneImage;
	@Transient
	private MultipartFile slideTwoImage;
	@Transient
	private MultipartFile slideThreeImage;
	@Transient
	private MultipartFile categoryOneImage;
	@Transient
	private MultipartFile categoryTwoImage;
	@Transient
	private MultipartFile categoryThreeImage;
	@Transient
	private MultipartFile categoryFourImage;
	
	@Transient
	private MultipartFile promotionOneImage;
	@Transient
	private MultipartFile promotionTwoImage;
	@Transient
	private MultipartFile offerOneImage;
	@Transient
	private MultipartFile offerTwoImage;
	@Transient
	private MultipartFile offerThreeImage;
	@Transient
	private MultipartFile offerFourImage;
	
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
	public String getCategoryOneLable2() {
		return categoryOneLable2;
	}
	public void setCategoryOneLable2(String categoryOneLable2) {
		this.categoryOneLable2 = categoryOneLable2;
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
	public String getCategoryTwoLable2() {
		return categoryTwoLable2;
	}
	public void setCategoryTwoLable2(String categoryTwoLable2) {
		this.categoryTwoLable2 = categoryTwoLable2;
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
	public String getCategoryThreeLable2() {
		return categoryThreeLable2;
	}
	public void setCategoryThreeLable2(String categoryThreeLable2) {
		this.categoryThreeLable2 = categoryThreeLable2;
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
	public String getCategoryFourLable2() {
		return categoryFourLable2;
	}
	public void setCategoryFourLable2(String categoryFourLable2) {
		this.categoryFourLable2 = categoryFourLable2;
	}
	
	public String getPromotionOneImg() {
		return promotionOneImg;
	}
	public void setPromotionOneImg(String promotionOneImg) {
		this.promotionOneImg = promotionOneImg;
	}
	public String getPromotionOneUrl() {
		return promotionOneUrl;
	}
	public void setPromotionOneUrl(String promotionOneUrl) {
		this.promotionOneUrl = promotionOneUrl;
	}
	public String getPromotionOneLable() {
		return promotionOneLable;
	}
	public void setPromotionOneLable(String promotionOneLable) {
		this.promotionOneLable = promotionOneLable;
	}
	public String getPromotionOneText() {
		return promotionOneText;
	}
	public void setPromotionOneText(String promotionOneText) {
		this.promotionOneText = promotionOneText;
	}
	public String getPromotionTwoImg() {
		return promotionTwoImg;
	}
	public void setPromotionTwoImg(String promotionTwoImg) {
		this.promotionTwoImg = promotionTwoImg;
	}
	public String getPromotionTwoUrl() {
		return promotionTwoUrl;
	}
	public void setPromotionTwoUrl(String promotionTwoUrl) {
		this.promotionTwoUrl = promotionTwoUrl;
	}
	public String getPromotionTwoLable() {
		return promotionTwoLable;
	}
	public void setPromotionTwoLable(String promotionTwoLable) {
		this.promotionTwoLable = promotionTwoLable;
	}
	public String getPromotionTwoText() {
		return promotionTwoText;
	}
	public void setPromotionTwoText(String promotionTwoText) {
		this.promotionTwoText = promotionTwoText;
	}
	public String getOfferOneImg() {
		return offerOneImg;
	}
	public void setOfferOneImg(String offerOneImg) {
		this.offerOneImg = offerOneImg;
	}
	public String getOfferOneTitle() {
		return offerOneTitle;
	}
	public void setOfferOneTitle(String offerOneTitle) {
		this.offerOneTitle = offerOneTitle;
	}
	public String getOfferOneText() {
		return offerOneText;
	}
	public void setOfferOneText(String offerOneText) {
		this.offerOneText = offerOneText;
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
	public String getOfferTwoTitle() {
		return offerTwoTitle;
	}
	public void setOfferTwoTitle(String offerTwoTitle) {
		this.offerTwoTitle = offerTwoTitle;
	}
	public String getOfferTwoText() {
		return offerTwoText;
	}
	public void setOfferTwoText(String offerTwoText) {
		this.offerTwoText = offerTwoText;
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
	public String getOfferThreeTitle() {
		return offerThreeTitle;
	}
	public void setOfferThreeTitle(String offerThreeTitle) {
		this.offerThreeTitle = offerThreeTitle;
	}
	public String getOfferThreeText() {
		return offerThreeText;
	}
	public void setOfferThreeText(String offerThreeText) {
		this.offerThreeText = offerThreeText;
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
	public String getOfferFourTitle() {
		return offerFourTitle;
	}
	public void setOfferFourTitle(String offerFourTitle) {
		this.offerFourTitle = offerFourTitle;
	}
	public String getOfferFourText() {
		return offerFourText;
	}
	public void setOfferFourText(String offerFourText) {
		this.offerFourText = offerFourText;
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
	public MultipartFile getSlideOneImage() {
		return slideOneImage;
	}
	public void setSlideOneImage(MultipartFile slideOneImage) {
		this.slideOneImage = slideOneImage;
	}
	public MultipartFile getSlideTwoImage() {
		return slideTwoImage;
	}
	public void setSlideTwoImage(MultipartFile slideTwoImage) {
		this.slideTwoImage = slideTwoImage;
	}
	public MultipartFile getSlideThreeImage() {
		return slideThreeImage;
	}
	public void setSlideThreeImage(MultipartFile slideThreeImage) {
		this.slideThreeImage = slideThreeImage;
	}
	public MultipartFile getCategoryOneImage() {
		return categoryOneImage;
	}
	public void setCategoryOneImage(MultipartFile categoryOneImage) {
		this.categoryOneImage = categoryOneImage;
	}
	public MultipartFile getCategoryTwoImage() {
		return categoryTwoImage;
	}
	public void setCategoryTwoImage(MultipartFile categoryTwoImage) {
		this.categoryTwoImage = categoryTwoImage;
	}
	public MultipartFile getCategoryThreeImage() {
		return categoryThreeImage;
	}
	public void setCategoryThreeImage(MultipartFile categoryThreeImage) {
		this.categoryThreeImage = categoryThreeImage;
	}
	public MultipartFile getCategoryFourImage() {
		return categoryFourImage;
	}
	public void setCategoryFourImage(MultipartFile categoryFourImage) {
		this.categoryFourImage = categoryFourImage;
	}
	
	public MultipartFile getPromotionOneImage() {
		return promotionOneImage;
	}
	public void setPromotionOneImage(MultipartFile promotionOneImage) {
		this.promotionOneImage = promotionOneImage;
	}
	public MultipartFile getPromotionTwoImage() {
		return promotionTwoImage;
	}
	public void setPromotionTwoImage(MultipartFile promotionTwoImage) {
		this.promotionTwoImage = promotionTwoImage;
	}
	public MultipartFile getOfferOneImage() {
		return offerOneImage;
	}
	public void setOfferOneImage(MultipartFile offerOneImage) {
		this.offerOneImage = offerOneImage;
	}
	public MultipartFile getOfferTwoImage() {
		return offerTwoImage;
	}
	public void setOfferTwoImage(MultipartFile offerTwoImage) {
		this.offerTwoImage = offerTwoImage;
	}
	public MultipartFile getOfferThreeImage() {
		return offerThreeImage;
	}
	public void setOfferThreeImage(MultipartFile offerThreeImage) {
		this.offerThreeImage = offerThreeImage;
	}
	public MultipartFile getOfferFourImage() {
		return offerFourImage;
	}
	public void setOfferFourImage(MultipartFile offerFourImage) {
		this.offerFourImage = offerFourImage;
	}
	
}

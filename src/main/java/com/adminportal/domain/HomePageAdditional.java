package com.adminportal.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class HomePageAdditional {
	@Id
	private Long id =(long) 1;
		//Special Banners
		private String specialOneImg;
		private String specialOneLable;
		private String specialOneLable2;
		private String specialOneLable3;
		private String specialOneUrl;
		private String specialOneText;
		private String specialTwoImg;
		private String specialTwoLable;
		private String specialTwoLable2;
		private String specialTwoUrl;
		private String specialThreeImg;
		private String specialThreeLable;
		private String specialThreeLable2;
		private String specialThreeUrl;
		private String specialThreeText;
		private String specialFourImg;
		private String specialFourLable;
		private String specialFourUrl;
		private String specialFourText;
		private String specialFiveLable;
		private String specialFiveUrl;
		private String specialFiveText;
		private String specialSixImg;
		private String specialSixLable;
		private String specialSixUrl;
		private String specialSixText;
		//Special Discount
		private String bannerOneImg;
		private String bannerOneUrl;
		private String bannerOneLable;
		private String bannerOneLable2;
		private String bannerTwoImg;
		private String bannerTwoUrl;
		private String bannerTwoLable;
		private String bannerTwoLable2;
		private String bannerThreeImg;
		private String bannerThreeUrl;
		private String bannerThreeLable;
		private String bannerThreeLable2;
		private String bannerFourImg;
		private String bannerFourUrl;
		private String bannerFourLable;
		private String bannerFourLable2;
		private String testimonialImg;
		@Transient
		private MultipartFile testimonialImage;
		@Transient
		private MultipartFile specialOneImage;
		@Transient
		private MultipartFile specialTwoImage;
		@Transient
		private MultipartFile specialThreeImage;
		@Transient
		private MultipartFile specialFourImage;
		@Transient
		private MultipartFile specialSixImage;
		@Transient
		private MultipartFile bannerOneImage;
		@Transient
		private MultipartFile bannerTwoImage;
		@Transient
		private MultipartFile bannerThreeImage;
		@Transient
		private MultipartFile bannerFourImage;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSpecialOneImg() {
			return specialOneImg;
		}
		public void setSpecialOneImg(String specialOneImg) {
			this.specialOneImg = specialOneImg;
		}
		public String getSpecialOneLable() {
			return specialOneLable;
		}
		public void setSpecialOneLable(String specialOneLable) {
			this.specialOneLable = specialOneLable;
		}
		public String getSpecialOneLable2() {
			return specialOneLable2;
		}
		public void setSpecialOneLable2(String specialOneLable2) {
			this.specialOneLable2 = specialOneLable2;
		}
		public String getSpecialOneLable3() {
			return specialOneLable3;
		}
		public void setSpecialOneLable3(String specialOneLable3) {
			this.specialOneLable3 = specialOneLable3;
		}
		public String getSpecialOneUrl() {
			return specialOneUrl;
		}
		public void setSpecialOneUrl(String specialOneUrl) {
			this.specialOneUrl = specialOneUrl;
		}
		public String getSpecialOneText() {
			return specialOneText;
		}
		public void setSpecialOneText(String specialOneText) {
			this.specialOneText = specialOneText;
		}
		public String getSpecialTwoImg() {
			return specialTwoImg;
		}
		public void setSpecialTwoImg(String specialTwoImg) {
			this.specialTwoImg = specialTwoImg;
		}
		public String getSpecialTwoLable() {
			return specialTwoLable;
		}
		public void setSpecialTwoLable(String specialTwoLable) {
			this.specialTwoLable = specialTwoLable;
		}
		public String getSpecialTwoLable2() {
			return specialTwoLable2;
		}
		public void setSpecialTwoLable2(String specialTwoLable2) {
			this.specialTwoLable2 = specialTwoLable2;
		}
		public String getSpecialTwoUrl() {
			return specialTwoUrl;
		}
		public void setSpecialTwoUrl(String specialTwoUrl) {
			this.specialTwoUrl = specialTwoUrl;
		}
		public String getSpecialThreeImg() {
			return specialThreeImg;
		}
		public void setSpecialThreeImg(String specialThreeImg) {
			this.specialThreeImg = specialThreeImg;
		}
		public String getSpecialThreeLable() {
			return specialThreeLable;
		}
		public void setSpecialThreeLable(String specialThreeLable) {
			this.specialThreeLable = specialThreeLable;
		}
		public String getSpecialThreeLable2() {
			return specialThreeLable2;
		}
		public void setSpecialThreeLable2(String specialThreeLable2) {
			this.specialThreeLable2 = specialThreeLable2;
		}
		public String getSpecialThreeUrl() {
			return specialThreeUrl;
		}
		public void setSpecialThreeUrl(String specialThreeUrl) {
			this.specialThreeUrl = specialThreeUrl;
		}
		public String getSpecialThreeText() {
			return specialThreeText;
		}
		public void setSpecialThreeText(String specialThreeText) {
			this.specialThreeText = specialThreeText;
		}
		public String getSpecialFourImg() {
			return specialFourImg;
		}
		public void setSpecialFourImg(String specialFourImg) {
			this.specialFourImg = specialFourImg;
		}
		public String getSpecialFourLable() {
			return specialFourLable;
		}
		public void setSpecialFourLable(String specialFourLable) {
			this.specialFourLable = specialFourLable;
		}
		public String getSpecialFourUrl() {
			return specialFourUrl;
		}
		public void setSpecialFourUrl(String specialFourUrl) {
			this.specialFourUrl = specialFourUrl;
		}
		public String getSpecialFourText() {
			return specialFourText;
		}
		public void setSpecialFourText(String specialFourText) {
			this.specialFourText = specialFourText;
		}
		public String getSpecialFiveLable() {
			return specialFiveLable;
		}
		public void setSpecialFiveLable(String specialFiveLable) {
			this.specialFiveLable = specialFiveLable;
		}
		public String getSpecialFiveUrl() {
			return specialFiveUrl;
		}
		public void setSpecialFiveUrl(String specialFiveUrl) {
			this.specialFiveUrl = specialFiveUrl;
		}
		public String getSpecialFiveText() {
			return specialFiveText;
		}
		public void setSpecialFiveText(String specialFiveText) {
			this.specialFiveText = specialFiveText;
		}
		public String getSpecialSixImg() {
			return specialSixImg;
		}
		public void setSpecialSixImg(String specialSixImg) {
			this.specialSixImg = specialSixImg;
		}
		public String getSpecialSixLable() {
			return specialSixLable;
		}
		public void setSpecialSixLable(String specialSixLable) {
			this.specialSixLable = specialSixLable;
		}
		public String getSpecialSixUrl() {
			return specialSixUrl;
		}
		public void setSpecialSixUrl(String specialSixUrl) {
			this.specialSixUrl = specialSixUrl;
		}
		public String getSpecialSixText() {
			return specialSixText;
		}
		public void setSpecialSixText(String specialSixText) {
			this.specialSixText = specialSixText;
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
		public String getBannerOneLable() {
			return bannerOneLable;
		}
		public void setBannerOneLable(String bannerOneLable) {
			this.bannerOneLable = bannerOneLable;
		}
		public String getBannerOneLable2() {
			return bannerOneLable2;
		}
		public void setBannerOneLable2(String bannerOneLable2) {
			this.bannerOneLable2 = bannerOneLable2;
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
		public String getBannerTwoLable() {
			return bannerTwoLable;
		}
		public void setBannerTwoLable(String bannerTwoLable) {
			this.bannerTwoLable = bannerTwoLable;
		}
		public String getBannerTwoLable2() {
			return bannerTwoLable2;
		}
		public void setBannerTwoLable2(String bannerTwoLable2) {
			this.bannerTwoLable2 = bannerTwoLable2;
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
		public String getBannerThreeLable() {
			return bannerThreeLable;
		}
		public void setBannerThreeLable(String bannerThreeLable) {
			this.bannerThreeLable = bannerThreeLable;
		}
		public String getBannerThreeLable2() {
			return bannerThreeLable2;
		}
		public void setBannerThreeLable2(String bannerThreeLable2) {
			this.bannerThreeLable2 = bannerThreeLable2;
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
		public String getBannerFourLable() {
			return bannerFourLable;
		}
		public void setBannerFourLable(String bannerFourLable) {
			this.bannerFourLable = bannerFourLable;
		}
		public String getBannerFourLable2() {
			return bannerFourLable2;
		}
		public void setBannerFourLable2(String bannerFourLable2) {
			this.bannerFourLable2 = bannerFourLable2;
		}
		
		
		public MultipartFile getBannerOneImage() {
			return bannerOneImage;
		}
		public void setBannerOneImage(MultipartFile bannerOneImage) {
			this.bannerOneImage = bannerOneImage;
		}
		public MultipartFile getBannerTwoImage() {
			return bannerTwoImage;
		}
		public void setBannerTwoImage(MultipartFile bannerTwoImage) {
			this.bannerTwoImage = bannerTwoImage;
		}
		public MultipartFile getBannerThreeImage() {
			return bannerThreeImage;
		}
		public void setBannerThreeImage(MultipartFile bannerThreeImage) {
			this.bannerThreeImage = bannerThreeImage;
		}
		public MultipartFile getBannerFourImage() {
			return bannerFourImage;
		}
		public void setBannerFourImage(MultipartFile bannerFourImage) {
			this.bannerFourImage = bannerFourImage;
		}
		public MultipartFile getSpecialOneImage() {
			return specialOneImage;
		}
		public void setSpecialOneImage(MultipartFile specialOneImage) {
			this.specialOneImage = specialOneImage;
		}
		public MultipartFile getSpecialTwoImage() {
			return specialTwoImage;
		}
		public void setSpecialTwoImage(MultipartFile specialTwoImage) {
			this.specialTwoImage = specialTwoImage;
		}
		public MultipartFile getSpecialThreeImage() {
			return specialThreeImage;
		}
		public void setSpecialThreeImage(MultipartFile specialThreeImage) {
			this.specialThreeImage = specialThreeImage;
		}
		public MultipartFile getSpecialFourImage() {
			return specialFourImage;
		}
		public void setSpecialFourImage(MultipartFile specialFourImage) {
			this.specialFourImage = specialFourImage;
		}
		public MultipartFile getSpecialSixImage() {
			return specialSixImage;
		}
		public void setSpecialSixImage(MultipartFile specialSixImage) {
			this.specialSixImage = specialSixImage;
		}
		public String getTestimonialImg() {
			return testimonialImg;
		}
		public void setTestimonialImg(String testimonialImg) {
			this.testimonialImg = testimonialImg;
		}
		public MultipartFile getTestimonialImage() {
			return testimonialImage;
		}
		public void setTestimonialImage(MultipartFile testimonialImage) {
			this.testimonialImage = testimonialImage;
		}
		
}

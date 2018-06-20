package com.adminportal.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String fullName;
	private String email;
	private String reason;
	private String helpFor;
	private String assistanceType;
	private String reasonType;
	private String orderNumber;
	private String details;
	private String contactImage;
	private Date contactDate;
	private Date respondedDate;
	private String status;
	@Transient
	private List<MultipartFile> productImage;
	
	@OneToMany(mappedBy = "contact",cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	private List<InquiryRespond> inquiryRespondList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getHelpFor() {
		return helpFor;
	}
	public void setHelpFor(String helpFor) {
		this.helpFor = helpFor;
	}
	public String getAssistanceType() {
		return assistanceType;
	}
	public void setAssistanceType(String assistanceType) {
		this.assistanceType = assistanceType;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getContactImage() {
		return contactImage;
	}
	public void setContactImage(String contactImage) {
		this.contactImage = contactImage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	public Date getRespondedDate() {
		return respondedDate;
	}
	public void setRespondedDate(Date respondedDate) {
		this.respondedDate = respondedDate;
	}
	public List<MultipartFile> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<MultipartFile> productImage) {
		this.productImage = productImage;
	}
	public List<InquiryRespond> getInquiryRespondList() {
		return inquiryRespondList;
	}
	public void setInquiryRespondList(List<InquiryRespond> inquiryRespondList) {
		this.inquiryRespondList = inquiryRespondList;
	}
	
}

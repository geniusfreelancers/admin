package com.adminportal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private String reason;
	private String helpFor;
	private String assistanceType;
	private String reasonType;
	private String orderNumber;
	private String details;
	private String contactImage;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
}

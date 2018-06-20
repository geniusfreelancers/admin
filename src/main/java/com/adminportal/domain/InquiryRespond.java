package com.adminportal.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InquiryRespond {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition="text")
	private String responseText;
	private boolean fromSystem;
	private String respondedBy;
	private Date respondDate;
	private boolean opened;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Contact contact;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public boolean isFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(boolean fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getRespondedBy() {
		return respondedBy;
	}

	public void setRespondedBy(String respondedBy) {
		this.respondedBy = respondedBy;
	}

	public Date getRespondDate() {
		return respondDate;
	}

	public void setRespondDate(Date respondDate) {
		this.respondDate = respondDate;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	
}

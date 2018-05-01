package com.adminportal.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="search_log")
public class SearchLog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date searchStarted;
	private Date searchEnded;
	private String searchKeyword;
	private int resultReturned;
	
	private String searchedOnPage;
	
	
	private String sessionId;
	private String usedBrowser;
	private String cartId; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getSearchStarted() {
		return searchStarted;
	}
	public void setSearchStarted(Date searchStarted) {
		this.searchStarted = searchStarted;
	}
	public Date getSearchEnded() {
		return searchEnded;
	}
	public void setSearchEnded(Date searchEnded) {
		this.searchEnded = searchEnded;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getResultReturned() {
		return resultReturned;
	}
	public void setResultReturned(int resultReturned) {
		this.resultReturned = resultReturned;
	}
	public String getSearchedOnPage() {
		return searchedOnPage;
	}
	public void setSearchedOnPage(String searchedOnPage) {
		this.searchedOnPage = searchedOnPage;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUsedBrowser() {
		return usedBrowser;
	}
	public void setUsedBrowser(String usedBrowser) {
		this.usedBrowser = usedBrowser;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	
	
}

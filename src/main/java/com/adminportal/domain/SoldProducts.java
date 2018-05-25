package com.adminportal.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SoldProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Product product;
	
	private int soldQty;
	
	private Date lastSold;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getSoldQty() {
		return soldQty;
	}

	public void setSoldQty(int soldQty) {
		this.soldQty = soldQty;
	}

	public Date getLastSold() {
		return lastSold;
	}

	public void setLastSold(Date lastSold) {
		this.lastSold = lastSold;
	}
	
	
	
	
}

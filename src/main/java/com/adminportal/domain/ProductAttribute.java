package com.adminportal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductAttribute {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String valueString;

	private Double valueNumber;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="product_attr_id")
	private ProductAttr productAttr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public Double getValueNumber() {
		return valueNumber;
	}
	public void setValueNumber(Double valueNumber) {
		this.valueNumber = valueNumber;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductAttr getProductAttr() {
		return productAttr;
	}
	public void setProductAttr(ProductAttr productAttr) {
		this.productAttr = productAttr;
	}
	
		
	
}

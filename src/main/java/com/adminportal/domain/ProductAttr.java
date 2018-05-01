package com.adminportal.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ProductAttr {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String attribute;
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "productAttr")
	@JsonIgnore
	private List<ProductAttribute> productAttribute;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public List<ProductAttribute> getProductAttribute() {
		return productAttribute;
	}
	public void setProductAttribute(List<ProductAttribute> productAttribute) {
		this.productAttribute = productAttribute;
	}
	
}

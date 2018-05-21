package com.adminportal.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SubSubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String subSubCategorySlug;
	private String subSubCategoryName;

	@ManyToOne
	@JoinColumn(name="subCategory_id")
	@JsonIgnore
	private SubCategory subCategory;

	
	@OneToMany(mappedBy="mainSubCategory")
	@JsonIgnore
	private List<Product> product;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubSubCategorySlug() {
		return subSubCategorySlug;
	}

	public void setSubSubCategorySlug(String subSubCategorySlug) {
		this.subSubCategorySlug = subSubCategorySlug;
	}

	public String getSubSubCategoryName() {
		return subSubCategoryName;
	}

	public void setSubSubCategoryName(String subSubCategoryName) {
		this.subSubCategoryName = subSubCategoryName;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
}

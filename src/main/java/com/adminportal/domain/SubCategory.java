package com.adminportal.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String subCategorySlug;
	private String subCategoryName;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "subCategory")
	@JsonIgnore
	private List<SubSubCategory> subSubCategory;

	@ManyToOne
	@JoinColumn(name="category_id")
	@JsonIgnore
	private Category category;

	@OneToMany(mappedBy="subCategory")
	@JsonIgnore
	private List<Product> product;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubCategorySlug() {
		return subCategorySlug;
	}

	public void setSubCategorySlug(String subCategorySlug) {
		this.subCategorySlug = subCategorySlug;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public List<SubSubCategory> getSubSubCategory() {
		return subSubCategory;
	}

	public void setSubSubCategory(List<SubSubCategory> subSubCategory) {
		this.subSubCategory = subSubCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	
	
}
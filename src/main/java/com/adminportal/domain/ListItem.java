package com.adminportal.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ListItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Product product;
	
	@OneToMany(mappedBy = "listItem")
	@JsonIgnore
	private List<ProductToListItem> productToListItemList;


	@ManyToOne
	@JoinColumn(name="wish_list_id")
	private WishList wishList;
	

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

	public List<ProductToListItem> getProductToListItemList() {
		return productToListItemList;
	}

	public void setProductToListItemList(List<ProductToListItem> productToListItemList) {
		this.productToListItemList = productToListItemList;
	}

	public WishList getWishList() {
		return wishList;
	}

	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}

	
	
	
}

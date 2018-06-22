package com.adminportal.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int qty;
	private BigDecimal subtotal;
	private BigDecimal stitchingTotal;
	private String productSize;
	/*@OneToOne
	private Product product;*/
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
/*	@OneToMany( cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "cartItem")
	@JsonIgnore
	private List<ProductToCartItem> productToCartItemList;*/


	@ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    @JsonIgnore
	private ShoppingCart shoppingCart;
		
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getStitchingTotal() {
		return stitchingTotal;
	}

	public void setStitchingTotal(BigDecimal stitchingTotal) {
		this.stitchingTotal = stitchingTotal;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

/*	public List<ProductToCartItem> getProductToCartItemList() {
		return productToCartItemList;
	}

	public void setProductToCartItemList(List<ProductToCartItem> productToCartItemList) {
		this.productToCartItemList = productToCartItemList;
	}*/

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}

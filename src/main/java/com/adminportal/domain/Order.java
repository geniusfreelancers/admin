package com.adminportal.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.adminportal.domain.BillingAddress;
import com.adminportal.domain.CartItem;
import com.adminportal.domain.OrderLog;
import com.adminportal.domain.Payment;
import com.adminportal.domain.ShippingAddress;
import com.adminportal.domain.User;

@Entity
@Table(name="user_order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Date orderDate;
	private String customerFname;
	private String customerLname;
	private Date shippingDate;
	private String shippingMethod;
	private String orderStatus;
	private String paymentType;
	private String paymentConfirm;
	private String orderType;
	private String orderEmail;
	private String orderPhone;
	private BigDecimal orderSubtotal;
	private BigDecimal estTax;
	private String promocodeApplied;
	private BigDecimal discount;
	private BigDecimal shippingCost;
	private BigDecimal orderTotal;
	private String shippingCarrier;
	private String trackingNumber;
	private Date estimatedDeliveryDate;
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	private List<CartItem> cartItemList;
	
	@OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
	private List<OrderLog> orderLogList;
	
	@OneToOne(cascade=CascadeType.ALL)
	private ShippingAddress shippingAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	private BillingAddress billingAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Payment payment;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerFname() {
		return customerFname;
	}

	public void setCustomerFname(String customerFname) {
		this.customerFname = customerFname;
	}

	public String getCustomerLname() {
		return customerLname;
	}

	public void setCustomerLname(String customerLname) {
		this.customerLname = customerLname;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public List<OrderLog> getOrderLogList() {
		return orderLogList;
	}

	public void setOrderLogList(List<OrderLog> orderLogList) {
		this.orderLogList = orderLogList;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentConfirm() {
		return paymentConfirm;
	}

	public void setPaymentConfirm(String paymentConfirm) {
		this.paymentConfirm = paymentConfirm;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public BigDecimal getOrderSubtotal() {
		return orderSubtotal;
	}

	public void setOrderSubtotal(BigDecimal orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}

	public BigDecimal getEstTax() {
		return estTax;
	}

	public void setEstTax(BigDecimal estTax) {
		this.estTax = estTax;
	}

	public String getPromocodeApplied() {
		return promocodeApplied;
	}

	public void setPromocodeApplied(String promocodeApplied) {
		this.promocodeApplied = promocodeApplied;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getShippingCarrier() {
		return shippingCarrier;
	}

	public void setShippingCarrier(String shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}
	
	
}

package com.ohgiraffers.restapi.purchase.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_order")
public class Order {
	
	@Id
	@Column(name = "order_code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderCode;
	
	@Column(name = "product_code")
	private int productCode;
	
	@Column(name = "order_member")
	private int orderMember;
	
	@Column(name = "order_phone")
	private String orderPhone;
	
	@Column(name = "order_email")
	private String orderEmail;
	
	@Column(name = "order_receiver")
	private String orderReceiver;
	
	@Column(name = "order_address")
	private String orderAddress;
	
	@Column(name = "order_amount")
	private String orderAmount;
	
	@Column(name = "order_date")
	private String orderDate;

	public Order() {
	}
	public Order(int orderCode, int productCode, int orderMember, String orderPhone, String orderEmail,
			String orderReceiver, String orderAddress, String orderAmount, String orderDate) {
		this.orderCode = orderCode;
		this.productCode = productCode;
		this.orderMember = orderMember;
		this.orderPhone = orderPhone;
		this.orderEmail = orderEmail;
		this.orderReceiver = orderReceiver;
		this.orderAddress = orderAddress;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}

	public int getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getOrderMember() {
		return orderMember;
	}

	public void setOrderMember(int orderMember) {
		this.orderMember = orderMember;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderReceiver() {
		return orderReceiver;
	}

	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderCode=" + orderCode +
				", productCode=" + productCode +
				", orderMember=" + orderMember +
				", orderPhone='" + orderPhone + '\'' +
				", orderEmail='" + orderEmail + '\'' +
				", orderReceiver='" + orderReceiver + '\'' +
				", orderAddress='" + orderAddress + '\'' +
				", orderAmount='" + orderAmount + '\'' +
				", orderDate='" + orderDate + '\'' +
				'}';
	}
}

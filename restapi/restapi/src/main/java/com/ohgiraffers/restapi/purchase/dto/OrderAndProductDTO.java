package com.ohgiraffers.restapi.purchase.dto;

import com.ohgiraffers.restapi.product.dto.ProductDTO;

public class OrderAndProductDTO {
	private int orderCode;
	private ProductDTO product;
	private int orderMember;
	private String orderPhone;
	private String orderEmail;
	private String orderReceiver;
	private String orderAddress;
	private String orderAmount;
	private String orderDate;
	
	public OrderAndProductDTO() {
	}
	public OrderAndProductDTO(int orderCode, ProductDTO product, int orderMember, String orderPhone, String orderEmail,
			String orderReceiver, String orderAddress, String orderAmount, String orderDate) {
		this.orderCode = orderCode;
		this.product = product;
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
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
		return "OrderAndProductDTO{" +
				"orderCode=" + orderCode +
				", product=" + product +
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

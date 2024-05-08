package com.ohgiraffers.restapi.purchase.dto;

public class PurchaseDTO {
	private String memberId;
	private String orderAddress;
	private int orderAmount;
	private String orderEmail;
	private String orderPhone;
	private String orderReceiver;
	private int productCode;
	
	public PurchaseDTO() {
	}
	public PurchaseDTO(String memberId, String orderAddress, int orderAmount, String orderEmail, String orderPhone,
			String orderReceiver, int productCode) {
		this.memberId = memberId;
		this.orderAddress = orderAddress;
		this.orderAmount = orderAmount;
		this.orderEmail = orderEmail;
		this.orderPhone = orderPhone;
		this.orderReceiver = orderReceiver;
		this.productCode = productCode;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
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
	public String getOrderReceiver() {
		return orderReceiver;
	}
	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "PurchaseDTO{" +
				"memberId='" + memberId + '\'' +
				", orderAddress='" + orderAddress + '\'' +
				", orderAmount=" + orderAmount +
				", orderEmail='" + orderEmail + '\'' +
				", orderPhone='" + orderPhone + '\'' +
				", orderReceiver='" + orderReceiver + '\'' +
				", productCode=" + productCode +
				'}';
	}
}

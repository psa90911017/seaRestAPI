package com.ohgiraffers.restapi.review.dto;

public class ReviewDTO {
    private int reviewCode;
    private int productCode;
    private int memberCode;
    private String reviewTitle;
    private String reviewContent;
    private String reviewCreateDate;
    
	public ReviewDTO() {
	}
	public ReviewDTO(int reviewCode, int productCode, int memberCode, String reviewTitle, String reviewContent,
			String reviewCreateDate) {
		this.reviewCode = reviewCode;
		this.productCode = productCode;
		this.memberCode = memberCode;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.reviewCreateDate = reviewCreateDate;
	}
	
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewCreateDate() {
		return reviewCreateDate;
	}
	public void setReviewCreateDate(String reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}

	@Override
	public String toString() {
		return "ReviewDTO{" +
				"reviewCode=" + reviewCode +
				", productCode=" + productCode +
				", memberCode=" + memberCode +
				", reviewTitle='" + reviewTitle + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", reviewCreateDate='" + reviewCreateDate + '\'' +
				'}';
	}
}

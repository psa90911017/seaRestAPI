package com.ohgiraffers.restapi.review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_review")
public class Review {
	
	@Id
	@Column(name = "review_code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewCode;
	
	@Column(name = "product_code")
    private int productCode;
	
	@Column(name = "member_code")
    private int memberCode;
	
	@Column(name = "review_title")
    private String reviewTitle;
	
	@Column(name = "review_content")
    private String reviewContent;
	
	@Column(name = "review_create_date")
    private String reviewCreateDate;

	public Review() {
	}
	public Review(int reviewCode, int productCode, int memberCode, String reviewTitle, String reviewContent,
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
		return "Review{" +
				"reviewCode=" + reviewCode +
				", productCode=" + productCode +
				", memberCode=" + memberCode +
				", reviewTitle='" + reviewTitle + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", reviewCreateDate='" + reviewCreateDate + '\'' +
				'}';
	}
}

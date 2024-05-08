package com.ohgiraffers.restapi.review.entity;

import com.ohgiraffers.restapi.member.entity.Member;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_review")
public class ReviewAndMember {
	
	@Id
	@Column(name = "review_code")
    private int reviewCode;
	
	@Column(name = "product_code")
    private int productCode;
	
	@ManyToOne
	@JoinColumn(name = "member_code")
    private Member member;
	
	@Column(name = "review_title")
    private String reviewTitle;
	
	@Column(name = "review_content")
    private String reviewContent;
	
	@Column(name = "review_create_date")
    private String reviewCreateDate;

	public ReviewAndMember() {
	}
	public ReviewAndMember(int reviewCode, int productCode, Member member, String reviewTitle, String reviewContent,
			String reviewCreateDate) {
		this.reviewCode = reviewCode;
		this.productCode = productCode;
		this.member = member;
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
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
		return "ReviewAndMember{" +
				"reviewCode=" + reviewCode +
				", productCode=" + productCode +
				", member=" + member +
				", reviewTitle='" + reviewTitle + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", reviewCreateDate='" + reviewCreateDate + '\'' +
				'}';
	}
}

package com.ohgiraffers.restapi.review.dto;

import com.ohgiraffers.restapi.member.dto.MemberDTO;

public class ReviewAndMemberDTO {
    private int reviewCode;
    private int productCode;
    private MemberDTO member;
    private String reviewTitle;
    private String reviewContent;
    private String reviewCreateDate;
    
	public ReviewAndMemberDTO() {
	}
	public ReviewAndMemberDTO(int reviewCode, int productCode, MemberDTO member, String reviewTitle,
			String reviewContent, String reviewCreateDate) {
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
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
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
		return "ReviewAndMemberDTO{" +
				"reviewCode=" + reviewCode +
				", productCode=" + productCode +
				", member=" + member +
				", reviewTitle='" + reviewTitle + '\'' +
				", reviewContent='" + reviewContent + '\'' +
				", reviewCreateDate='" + reviewCreateDate + '\'' +
				'}';
	}
}

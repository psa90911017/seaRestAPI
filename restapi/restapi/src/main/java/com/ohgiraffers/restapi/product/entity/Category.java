package com.ohgiraffers.restapi.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category")
public class Category {
	
	@Id
	@Column(name = "category_code")
	private int categoryCode;
	
	@Column(name = "category_name")
	private String categoryName;
	
	public Category() {
	}
	public Category(int categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category{" +
				"categoryCode=" + categoryCode +
				", categoryName='" + categoryName + '\'' +
				'}';
	}
}

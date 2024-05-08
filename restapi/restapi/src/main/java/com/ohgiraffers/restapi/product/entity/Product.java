package com.ohgiraffers.restapi.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_product")
public class Product {
	
	@Id
	@Column(name = "product_code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productCode;
	
	@Column(name = "product_name")
    private String productName;
	
	@Column(name = "product_price")
    private String productPrice;
	
	@Column(name = "product_description")
    private String productDescription;
	
	@Column(name = "product_orderable")
    private String productOrderable;
	
	@Column(name = "category_code")
	private int categoryCode;

	@Column(name = "product_image_url")
    private String productImageUrl;
	
	@Column(name = "product_stock")
    private Long productStock;

	public Product() {
	}

	public Product(int productCode, String productName, String productPrice, String productDescription,
			String productOrderable, int categoryCode, String productImageUrl, Long productStock) {
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productOrderable = productOrderable;
		this.categoryCode = categoryCode;
		this.productImageUrl = productImageUrl;
		this.productStock = productStock;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductOrderable() {
		return productOrderable;
	}

	public void setProductOrderable(String productOrderable) {
		this.productOrderable = productOrderable;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public Long getProductStock() {
		return productStock;
	}

	public void setProductStock(Long productStock) {
		this.productStock = productStock;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productCode=" + productCode +
				", productName='" + productName + '\'' +
				", productPrice='" + productPrice + '\'' +
				", productDescription='" + productDescription + '\'' +
				", productOrderable='" + productOrderable + '\'' +
				", categoryCode=" + categoryCode +
				", productImageUrl='" + productImageUrl + '\'' +
				", productStock=" + productStock +
				'}';
	}
}

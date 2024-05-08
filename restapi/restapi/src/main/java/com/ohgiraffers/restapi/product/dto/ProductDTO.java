package com.ohgiraffers.restapi.product.dto;

public class ProductDTO {
	private int productCode;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String productOrderable;
    private int categoryCode;
    private String productImageUrl;
    private Long productStock;
    
	public ProductDTO() {
	}
	public ProductDTO(int productCode, String productName, String productPrice, String productDescription,
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
		return "ProductDTO{" +
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

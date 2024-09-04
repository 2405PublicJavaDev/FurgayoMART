package com.mart.boot.product.model.vo;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

public class ProductVO {
	private String pName;
	private int pPrice;
	private Date regDate;
	private boolean Manager;
	private List<ProductImageVO> productList;
	private int productNo;
	private String imageUrl;
	private String component;
	private String content;
	private String categoryName;
	
	public ProductVO() {}
	
//	public updateDetail() {}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public boolean isManager() {
		return Manager;
	}

	public void setManager(boolean manager) {
		Manager = manager;
	}

	public List<ProductImageVO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductImageVO> productList) {
		this.productList = productList;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductVO [pName=" + pName + ", pPrice=" + pPrice + ", regDate=" + regDate + ", Manager=" + Manager
				+ ", productList=" + productList + ", productNo=" + productNo + ", imageUrl=" + imageUrl
				+ ", component=" + component + ", content=" + content + ", categoryName=" + categoryName + "]";
	}
	
}

package com.mart.boot.product.model.vo;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

public class ProductVO {
	private int pNo; 							// 상품번호
	private String pName;							// 상품명
	private String pInput;							// 입고량
	private String pOutput;							// 출고량
	private String Expiration;						// 유통기한
	private Date regDate;							// 등록일자
	private int categoryNo;							// 상품 유형 번호
	private int Sale;								// 세일
	private int pPrice;								// 정가
	private String imageUrl;						// 이미지
	private List<ProductImageVO> productList;		// 이미지 리스트
	
	
	public ProductVO () {}


	public int getpNo() {
		return pNo;
	}


	public void setpNo(int pNo) {
		this.pNo = pNo;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public String getpInput() {
		return pInput;
	}


	public void setpInput(String pInput) {
		this.pInput = pInput;
	}


	public String getpOutput() {
		return pOutput;
	}


	public void setpOutput(String pOutput) {
		this.pOutput = pOutput;
	}


	public String getExpiration() {
		return Expiration;
	}


	public void setExpiration(String expiration) {
		Expiration = expiration;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public int getCategoryNo() {
		return categoryNo;
	}


	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}


	public int getSale() {
		return Sale;
	}


	public void setSale(int sale) {
		Sale = sale;
	}


	public int getpPrice() {
		return pPrice;
	}


	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public List<ProductImageVO> getProductList() {
		return productList;
	}


	public void setProductList(List<ProductImageVO> productList) {
		this.productList = productList;
	}


	@Override
	public String toString() {
		return "ProductVO [pNo=" + pNo + ", pName=" + pName + ", pInput=" + pInput + ", pOutput=" + pOutput
				+ ", Expiration=" + Expiration + ", regDate=" + regDate + ", categoryNo=" + categoryNo + ", Sale="
				+ Sale + ", pPrice=" + pPrice + ", imageUrl=" + imageUrl + ", productList=" + productList + "]";
	}
	
	
	
}

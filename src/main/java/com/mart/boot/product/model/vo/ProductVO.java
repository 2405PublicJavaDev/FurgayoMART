package com.mart.boot.product.model.vo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	private int pNo;							// 상품번호
	private String pName;						// 상품명
	private String pInput;						// 입고량
	private String pOutput;						// 출고량
	private String expiration;					// 유통기한	
	private Date regDate;						// 등록일자
	private int categoryNo;						// 상품 유형 번호
	private int sale;							// 세일
	private int pPrice;							// 정가
	private String imageUrl;					// 이미지
	private List<ProductImageVO> productList;	// 이미지 리스트
	// 파일 업로드를 위한 필드 추가
    private MultipartFile imgMain;
    private MultipartFile imgCook;
    private MultipartFile imgComponent;

	public ProductVO() {}
	
	public ProductDetailVO productDetail;
	
	// 남은 일수를 계산
	public long getRemainingDays() {
		try {
	        // expiration이 숫자인 경우 (예: '7')
	        int expirationDays = Integer.parseInt(expiration);
	        LocalDate regLocalDate = regDate.toLocalDate();  // 등록일자를 LocalDate로 변환
	        LocalDate expirationDate = regLocalDate.plusDays(expirationDays);  // 등록일로부터 유통기한 추가
	        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);  // 현재 날짜와 유통기한까지 남은 일 수 계산
	    } catch (NumberFormatException e) {
	        // expiration이 숫자가 아닌 경우 (날짜 문자열인 경우)
	        LocalDate expirationDate = LocalDate.parse(expiration);  // 문자열을 LocalDate로 변환
	        return ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);  // 현재 날짜와 유통기한까지 남은 일 수 계산
	    }
	}
	
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
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
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
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
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
	

	public MultipartFile getImgMain() {
		return imgMain;
	}

	public void setImgMain(MultipartFile imgMain) {
		this.imgMain = imgMain;
	}

	public MultipartFile getImgCook() {
		return imgCook;
	}

	public void setImgCook(MultipartFile imgCook) {
		this.imgCook = imgCook;
	}

	public MultipartFile getImgComponent() {
		return imgComponent;
	}

	public void setImgComponent(MultipartFile imgComponent) {
		this.imgComponent = imgComponent;
	}

	@Override
	public String toString() {
		return "ProductVO [pNo=" + pNo + ", pName=" + pName + ", pInput=" + pInput + ", pOutput=" + pOutput
				+ ", expiration=" + expiration + ", regDate=" + regDate + ", categoryNo=" + categoryNo + ", sale="
				+ sale + ", pPrice=" + pPrice + ", imageUrl=" + imageUrl + ", productList=" + productList + ", imgMain="
				+ imgMain + ", imgCook=" + imgCook + ", imgComponent=" + imgComponent + "]";
	}

}

package com.mart.boot.product.model.vo;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ProductDetailVO {
	private int pNo;
	private String imgMain;		// 메인 이미지
	private String imgCook;		// 조리 이미지
	private String imgComponent;// 상품 구성 이미지
	private String pComponent;	// 상품 구성
	private String cook;		// 조리법
	private String content;		// 추가 정보
	private int categoryNo;		// 상품 유형 번호
	// 파일 업로드를 위한 필드 추가
    private MultipartFile imgMainFile;
    private MultipartFile imgCookFile;
    private MultipartFile imgComponentFile;
    // 이미지 리스트(Maim, Cook, Component) 필드 추가
    private List<ProductImageVO> productImages;
    
    public ProductDetailVO() {}

	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getImgMain() {
		return imgMain;
	}
	public void setImgMain(String imgMain) {
		this.imgMain = imgMain;
	}
	public String getImgCook() {
		return imgCook;
	}
	public void setImgCook(String imgCook) {
		this.imgCook = imgCook;
	}
	public String getImgComponent() {
		return imgComponent;
	}
	public void setImgComponent(String imgComponent) {
		this.imgComponent = imgComponent;
	}
	public String getpComponent() {
		return pComponent;
	}
	public void setpComponent(String pComponent) {
		this.pComponent = pComponent;
	}
	public String getCook() {
		return cook;
	}
	public void setCook(String cook) {
		this.cook = cook;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public MultipartFile getImgMainFile() {
		return imgMainFile;
	}
	public void setImgMainFile(MultipartFile imgMainFile) {
		this.imgMainFile = imgMainFile;
	}
	public MultipartFile getImgCookFile() {
		return imgCookFile;
	}
	public void setImgCookFile(MultipartFile imgCookFile) {
		this.imgCookFile = imgCookFile;
	}
	public MultipartFile getImgComponentFile() {
		return imgComponentFile;
	}
	public void setImgComponentFile(MultipartFile imgComponentFile) {
		this.imgComponentFile = imgComponentFile;
	}
	
	public List<ProductImageVO> getProductImages() {
		return productImages;
	}
	
	public void setProductImages(List<ProductImageVO> productImages) {
		this.productImages = productImages;
	}
	
	@Override
	public String toString() {
		return "ProductDetailVO [pNo=" + pNo + ", imgMain=" + imgMain + ", imgCook=" + imgCook + ", imgComponent="
				+ imgComponent + ", pComponent=" + pComponent + ", cook=" + cook + ", content=" + content
				+ ", categoryNo=" + categoryNo + ", imgMainFile=" + imgMainFile + ", imgCookFile=" + imgCookFile
				+ ", imgComponentFile=" + imgComponentFile + "]";
	}

}

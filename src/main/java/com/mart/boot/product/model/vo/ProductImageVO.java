package com.mart.boot.product.model.vo;

public class ProductImageVO {
	private int imageId;		// 이미지ID
	private String fileName;	// 원본 파일명
	private String fileRename;	// 변경된 파일명
	private String filePath;	// 파일 위치
	private String fileSize;	// 파일 크기
	private int pNo;			// 상품 번호
	private String imageType;	// 이미지 타입(메인, 조리, 상품 구성)
	
	public ProductImageVO() {}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String toString() {
		return "ProductImageVO [imageId=" + imageId + ", fileName=" + fileName + ", fileRename=" + fileRename
				+ ", filePath=" + filePath + ", fileSize=" + fileSize + ", pNo=" + pNo + ", imageType="
				+ imageType + "]";
	}
}

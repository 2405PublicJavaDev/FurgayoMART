package com.mart.boot.product.model.vo;

public class ProductImageVO {
	private int imageId;
	private String fileName;
	private String fileRename;
	private String filePath;
	private String fileSize;
	private int productNo;
	
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

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "ProductImageVO [imageId=" + imageId + ", fileName=" + fileName + ", fileRename=" + fileRename
				+ ", filePath=" + filePath + ", fileSize=" + fileSize + ", productNo=" + productNo + "]";
	}
	
}

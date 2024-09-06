package com.mart.boot.product.model.vo;

import org.springframework.web.multipart.MultipartFile;


public class ProductFile {

	private int fileNo;
	private String fileName;
	private String fileRename;
	private String filePath;
	private int noticeNo;
	
	
	private MultipartFile uploadFile;


	public int getFileNo() {
		return fileNo;
	}


	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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


	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}


	public MultipartFile getUploadFile() {
		return uploadFile;
	}


	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}


	@Override
	public String toString() {
		return "ProductFile [fileNo=" + fileNo + ", fileName=" + fileName + ", fileRename=" + fileRename + ", filePath="
				+ filePath + ", noticeNo=" + noticeNo + ", uploadFile=" + uploadFile + "]";
	}
	
	
	
	
}

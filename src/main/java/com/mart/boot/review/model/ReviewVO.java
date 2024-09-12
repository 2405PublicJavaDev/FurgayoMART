package com.mart.boot.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

public class ReviewVO {
    private Long reviewId;
    private String writerId;
    private String userId;
    private String reTitle;
    private String reContent;
    private Date reviewDate;
    private String pName;
    private Date reModify;
    @JsonIgnore
    private MultipartFile attachedFile; // 업로드된 파일
    private String fileName; // 업로드된 파일의 실제 파일 이름

    // Getters and Setters
    public Long getReviewId() {
        return reviewId;
    }
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getWriterId() {
        return writerId;
    }
    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReTitle() {
        return reTitle;
    }
    public void setReTitle(String reTitle) {
        this.reTitle = reTitle;
    }

    public String getReContent() {
        return reContent;
    }
    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getPName() {
        return pName;
    }
    public void setPName(String pName) {
        this.pName = pName;
    }

    public Date getReModify() {
        return reModify;
    }
    public void setReModify(Date reModify) {
        this.reModify = reModify;
    }

    public MultipartFile getAttachedFile() {
        return attachedFile;
    }
    public void setAttachedFile(MultipartFile attachedFile) {
        this.attachedFile = attachedFile;
        if (attachedFile != null) {
            this.fileName = attachedFile.getOriginalFilename(); // 파일 이름 설정
        }
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

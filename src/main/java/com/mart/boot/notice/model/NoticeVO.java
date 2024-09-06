package com.mart.boot.notice.model;

import java.sql.Timestamp;

public class NoticeVO {

    private Integer noticeNo; // 공지사항 번호
    private String noticeTitle; // 공지 제목
    private String noticeContent; // 내용
    private String noticeWriter; // 작성자
    private Timestamp noticeDate; // 등록일자
    private String userId; //아이디
    private Timestamp noticeModify; // 수정일자
    private Integer noticePinned; // 고정 공지







    public NoticeVO(Integer noticePinned, Timestamp noticeModify, String userId, Timestamp noticeDate, String noticeWriter, String noticeContent, String noticeTitle, Integer noticeNo) {
        this.noticePinned = noticePinned;
        this.noticeModify = noticeModify;
        this.userId = userId;
        this.noticeDate = noticeDate;
        this.noticeWriter = noticeWriter;
        this.noticeContent = noticeContent;
        this.noticeTitle = noticeTitle;
        this.noticeNo = noticeNo;
    }








    @Override
    public String toString() {
        return "NoticeVO{" +
                "noticeNo=" + noticeNo +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeWriter='" + noticeWriter + '\'' +
                ", noticeDate=" + noticeDate +
                ", userId='" + userId + '\'' +
                ", noticeModify=" + noticeModify +
                ", noticePinned=" + noticePinned +
                '}';
    }




    public Integer getNoticePinned() {
        return noticePinned;
    }

    public void setNoticePinned(Integer noticePinned) {
        this.noticePinned = noticePinned;
    }

    public Timestamp getNoticeModify() {
        return noticeModify;
    }

    public void setNoticeModify(Timestamp noticeModify) {
        this.noticeModify = noticeModify;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeWriter() {
        return noticeWriter;
    }

    public void setNoticeWriter(String noticeWriter) {
        this.noticeWriter = noticeWriter;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Integer getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(Integer noticeNo) {
        this.noticeNo = noticeNo;
    }


}

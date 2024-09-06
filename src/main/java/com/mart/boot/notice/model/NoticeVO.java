package com.mart.boot.notice.model;

import java.sql.Timestamp;

public class NoticeVO {

    private Integer noticeNo; // 공지사항 번호
    private String noticeTitle; // 공지 제목
    private String noticeContent; // 내용
    private String noticeWriter; // 작성자
    private Timestamp noticeDate; // 등록일자
    private String userId; // 아이디
    private Timestamp noticeModify; // 수정일자
    private Integer noticePinned; // 고정 공지

    // 기본 생성자 추가
    public NoticeVO() {
    }

    // 모든 필드를 포함하는 생성자
    public NoticeVO(Integer noticeNo, String noticeTitle, String noticeContent, String noticeWriter,
                    Timestamp noticeDate, String userId, Timestamp noticeModify, Integer noticePinned) {
        this.noticeNo = noticeNo;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeWriter = noticeWriter;
        this.noticeDate = noticeDate;
        this.userId = userId;
        this.noticeModify = noticeModify;
        this.noticePinned = noticePinned;
    }

    // Builder 패턴 적용 (선택적)
    public static class Builder {
        private Integer noticeNo;
        private String noticeTitle;
        private String noticeContent;
        private String noticeWriter;
        private Timestamp noticeDate;
        private String userId;
        private Timestamp noticeModify;
        private Integer noticePinned;

        public Builder noticeNo(Integer noticeNo) {
            this.noticeNo = noticeNo;
            return this;
        }

        public Builder noticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
            return this;
        }

        public Builder noticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
            return this;
        }

        public Builder noticeWriter(String noticeWriter) {
            this.noticeWriter = noticeWriter;
            return this;
        }

        public Builder noticeDate(Timestamp noticeDate) {
            this.noticeDate = noticeDate;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder noticeModify(Timestamp noticeModify) {
            this.noticeModify = noticeModify;
            return this;
        }

        public Builder noticePinned(Integer noticePinned) {
            this.noticePinned = noticePinned;
            return this;
        }

        public NoticeVO build() {
            return new NoticeVO(noticeNo, noticeTitle, noticeContent, noticeWriter,
                    noticeDate, userId, noticeModify, noticePinned);
        }
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

    // Getter와 Setter 메서드들
    public Integer getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(Integer noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeWriter() {
        return noticeWriter;
    }

    public void setNoticeWriter(String noticeWriter) {
        this.noticeWriter = noticeWriter;
    }

    public Timestamp getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Timestamp noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getNoticeModify() {
        return noticeModify;
    }

    public void setNoticeModify(Timestamp noticeModify) {
        this.noticeModify = noticeModify;
    }

    public Integer getNoticePinned() {
        return noticePinned;
    }

    public void setNoticePinned(Integer noticePinned) {
        this.noticePinned = noticePinned;
    }
}
package com.mart.boot.notice.service;

import com.mart.boot.notice.model.NoticeVO;

import java.util.List;

public interface NoticeService {
    List<NoticeVO> selectAllNotices();

    NoticeVO selectNoticeById(int noticeNo);

    int insertNotice(NoticeVO noticeVO);

    int updateNotice(NoticeVO noticeVO);

    int deleteNotice(int noticeNo);

    List<NoticeVO> selectNoticesWithPaging(int page, int size);

    int getTotalNoticeCount();
}

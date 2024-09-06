package com.mart.boot.notice.store;

import com.mart.boot.notice.model.NoticeVO;

import java.util.List;

public interface NoticeStore {
    List<NoticeVO> selectAllNotices();
    int insertNotice(NoticeVO noticeVO);
    int updateNotice(NoticeVO noticeVO);
    int deleteNotice(int noticeNo);
    NoticeVO selectNoticeById(int noticeNo);

    int getTotalNoticeCount();

    List<NoticeVO> selectNoticesWithPaging(int offset, int size);
}

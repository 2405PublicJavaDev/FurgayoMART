package com.mart.boot.notice.service.impl;

import com.mart.boot.notice.model.NoticeVO;
import com.mart.boot.notice.store.NoticeStore;
import com.mart.boot.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeStore noticeStore;

    // 공지사항 전체 조회
    @Override
    public List<NoticeVO> selectAllNotices() {
        return noticeStore.selectAllNotices();
    }

    // 공지사항 등록
    @Override
    public int insertNotice(NoticeVO noticeVO) {
        return noticeStore.insertNotice(noticeVO);
    }

    // 공지사항 수정
    @Override
    public int updateNotice(NoticeVO noticeVO) {
        return noticeStore.updateNotice(noticeVO);
    }

    // 공지사항 삭제
    @Override
    public int deleteNotice(int noticeNo) {
        return noticeStore.deleteNotice(noticeNo);
    }

    // 공지사항 ID로 조회
    @Override
    public NoticeVO selectNoticeById(int noticeNo) {
        return noticeStore.selectNoticeById(noticeNo);
    }

    // 페이징 처리된 공지사항 목록 조회
    @Override
    public List<NoticeVO> selectNoticesWithPaging(int page, int size) {
        return noticeStore.selectNoticesWithPaging(page, size);
    }

    // 공지사항 총 개수 조회
    @Override
    public int getTotalNoticeCount() {
        return noticeStore.getTotalNoticeCount();
    }
}
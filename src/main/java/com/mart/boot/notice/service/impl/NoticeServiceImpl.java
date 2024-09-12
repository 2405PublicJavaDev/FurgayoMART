package com.mart.boot.notice.service.impl;

import com.mart.boot.notice.model.NoticeVO;
import com.mart.boot.notice.store.NoticeStore;
import com.mart.boot.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeStore noticeStore;

    // 모든 공지사항 조회 (페이징 없이)
    @Override
    public List<NoticeVO> selectAllNotices() {
        return noticeStore.selectAllNotices();
    }

    // 검색 조건에 따른 공지사항 조회
    @Override
    public List<NoticeVO> selectAllNotices(String searchType, String searchKeyword) {
        logger.info("Fetching notices with searchType: {}, searchKeyword: {}", searchType, searchKeyword);
        return noticeStore.selectNotices(searchType, searchKeyword);
    }

    // 공지사항 단건 조회
    @Override
    public NoticeVO selectNoticeById(int noticeNo) {
        return noticeStore.selectNoticeById(noticeNo);
    }

    // 공지사항 등록
    @Override
    public int insertNotice(NoticeVO noticeVO) {
        logger.info("Inserting notice: {}", noticeVO);
        int result = noticeStore.insertNotice(noticeVO);
        logger.info("Insert result: {}", result);
        return result;
    }

    // 공지사항 수정
    @Override
    public int updateNotice(NoticeVO noticeVO) {
        logger.info("Updating notice: {}", noticeVO);
        return noticeStore.updateNotice(noticeVO);
    }

    // 공지사항 삭제
    @Override
    public int deleteNotice(int noticeNo) {
        logger.info("Deleting notice with ID: {}", noticeNo);
        return noticeStore.deleteNotice(noticeNo);
    }

    // 총 공지사항 개수 조회 (전체)
    @Override
    public int getTotalNoticeCount() {
        return noticeStore.getTotalNoticeCount();
    }

    // 검색 조건에 따른 공지사항 개수 조회
    @Override
    public int getTotalNoticeCount(String searchType, String searchKeyword) {
        logger.info("Fetching total notice count with searchType: {}, searchKeyword: {}", searchType, searchKeyword);
        return noticeStore.countNotices(searchType, searchKeyword);
    }
    @Override
    public List<NoticeVO> selectPinnedNotices() {
        return noticeStore.selectPinnedNotices();
    }
}

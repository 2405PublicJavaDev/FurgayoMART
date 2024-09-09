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

    @Override
    public List<NoticeVO> selectAllNotices() {
        return noticeStore.selectAllNotices();
    }

    @Override
    public List<NoticeVO> selectAllNotices(String searchType, String searchKeyword) {
        return noticeStore.selectNotices(searchType, searchKeyword);
    }

    @Override
    public NoticeVO selectNoticeById(int noticeNo) {
        return noticeStore.selectNoticeById(noticeNo);
    }

    @Override
    public int insertNotice(NoticeVO noticeVO) {
        logger.info("Inserting notice in service layer: {}", noticeVO);
        int result = noticeStore.insertNotice(noticeVO);
        logger.info("Insert result from store: {}", result);
        return result;
    }

    @Override
    public int updateNotice(NoticeVO noticeVO) {
        return noticeStore.updateNotice(noticeVO);
    }

    @Override
    public int deleteNotice(int noticeNo) {
        return noticeStore.deleteNotice(noticeNo);
    }

    @Override
    public int getTotalNoticeCount() {
        return noticeStore.getTotalNoticeCount();
    }

    @Override
    public int getTotalNoticeCount(String searchType, String searchKeyword) {
        return noticeStore.countNotices(searchType, searchKeyword);
    }
}

package com.mart.boot.notice.service;

import com.mart.boot.notice.model.NoticeVO;

import java.util.List;

public interface NoticeService {

    // 모든 공지사항 조회
    List<NoticeVO> selectAllNotices();

    // 검색 조건에 따른 공지사항 조회
    List<NoticeVO> selectAllNotices(String searchType, String searchKeyword);

    // 공지사항 단건 조회
    NoticeVO selectNoticeById(int noticeNo);

    // 공지사항 등록
    int insertNotice(NoticeVO noticeVO);

    // 공지사항 수정
    int updateNotice(NoticeVO noticeVO);

    // 공지사항 삭제
    int deleteNotice(int noticeNo);

    // 총 공지사항 개수 조회 (전체)
    int getTotalNoticeCount();

    // 검색 조건에 따른 공지사항 개수 조회
    int getTotalNoticeCount(String searchType, String searchKeyword);

    List<NoticeVO> selectPinnedNotices();
}

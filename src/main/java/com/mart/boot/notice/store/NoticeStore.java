package com.mart.boot.notice.store;

import com.mart.boot.notice.model.NoticeVO;

import java.util.List;
import java.util.Map;

public interface NoticeStore {
    // 모든 공지사항 조회
    List<NoticeVO> selectAllNotices();

    // 공지사항 등록
    int insertNotice(NoticeVO noticeVO);

    // 공지사항 수정
    int updateNotice(NoticeVO noticeVO);

    // 공지사항 삭제
    int deleteNotice(int noticeNo);

    // 공지사항 단건 조회
    NoticeVO selectNoticeById(int noticeNo);

    // 총 공지사항 개수 조회
    int getTotalNoticeCount();

    // 페이징을 위한 메서드 추가
    List<NoticeVO> selectNoticesWithPaging(Map<String, Object> params);

    // 검색 조건에 따른 공지사항 조회
    List<NoticeVO> selectNotices(String searchType, String searchKeyword);

    // 검색 조건에 따른 공지사항 개수 조회
    int countNotices(String searchType, String searchKeyword);
}

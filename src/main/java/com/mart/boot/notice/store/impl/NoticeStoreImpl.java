package com.mart.boot.notice.store.impl;

import com.mart.boot.notice.model.NoticeVO;
import com.mart.boot.notice.store.NoticeStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoticeStoreImpl implements NoticeStore {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.mart.boot.notice.mapper.NoticeMapper.";

    @Override
    public List<NoticeVO> selectAllNotices() {
        return sqlSession.selectList(NAMESPACE + "selectAllNotices");
    }

    @Override
    public int insertNotice(NoticeVO noticeVO) {
        return sqlSession.insert(NAMESPACE + "insertNotice", noticeVO);
    }

    @Override
    public int updateNotice(NoticeVO noticeVO) {
        return sqlSession.update(NAMESPACE + "updateNotice", noticeVO);
    }

    @Override
    public int deleteNotice(int noticeNo) {
        return sqlSession.delete(NAMESPACE + "deleteNotice", noticeNo);
    }

    @Override
    public NoticeVO selectNoticeById(int noticeNo) {
        return sqlSession.selectOne(NAMESPACE + "selectNoticeById", noticeNo);
    }

    @Override
    public int getTotalNoticeCount() {
        return sqlSession.selectOne(NAMESPACE + "getTotalNoticeCount");
    }

    @Override
    public List<NoticeVO> selectNoticesWithPaging(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + "selectNoticesWithPaging", params);
    }

    // 검색 조건에 따른 공지사항 조회
    @Override
    public List<NoticeVO> selectNotices(String searchType, String searchKeyword) {
        SearchParams searchParams = new SearchParams(searchType, searchKeyword);
        return sqlSession.selectList(NAMESPACE + "selectNotices", searchParams);
    }

    // 검색 조건에 따른 공지사항 개수 조회
    @Override
    public int countNotices(String searchType, String searchKeyword) {
        SearchParams searchParams = new SearchParams(searchType, searchKeyword);
        return sqlSession.selectOne(NAMESPACE + "countNotices", searchParams);
    }

    // 검색 파라미터를 전달하기 위한 내부 클래스
    private static class SearchParams {
        private String searchType;
        private String searchKeyword;

        public SearchParams(String searchType, String searchKeyword) {
            this.searchType = searchType;
            this.searchKeyword = searchKeyword;
        }

        public String getSearchType() {
            return searchType;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }

        public String getSearchKeyword() {
            return searchKeyword;
        }

        public void setSearchKeyword(String searchKeyword) {
            this.searchKeyword = searchKeyword;
        }
    }
    public List<NoticeVO> selectPinnedNotices() {
        return sqlSession.selectList(NAMESPACE + "selectPinnedNotices");
    }
}

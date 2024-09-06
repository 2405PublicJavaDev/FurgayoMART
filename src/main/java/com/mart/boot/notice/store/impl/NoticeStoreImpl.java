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
    public List<NoticeVO> selectNoticesWithPaging(int offset, int size) {
        return sqlSession.selectList(NAMESPACE + "selectNoticesWithPaging", Map.of("offset", offset, "size", size));
    }
}

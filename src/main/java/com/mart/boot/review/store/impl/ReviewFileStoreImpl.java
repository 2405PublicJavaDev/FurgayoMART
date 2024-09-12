package com.mart.boot.review.store.impl;

import com.mart.boot.review.store.ReviewFileStore;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReviewFileStoreImpl implements ReviewFileStore {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void addReviewFile(Long reviewId, String fileName) {
        Map<String, Object> params = new HashMap<>();
        params.put("reviewId", reviewId);
        params.put("fileName", fileName);
        sqlSession.insert("ReviewFile.insertReviewFile", params);
    }
}
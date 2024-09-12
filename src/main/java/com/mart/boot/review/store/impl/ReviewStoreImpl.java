package com.mart.boot.review.store.impl;

import com.mart.boot.review.model.ReviewVO;
import com.mart.boot.review.store.ReviewStore;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewStoreImpl implements ReviewStore {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "Review";

    @Override
    public List<ReviewVO> getAllReviews() {
        return sqlSession.selectList(namespace + ".selectAllReviews");
    }

    @Override
    public List<ReviewVO> getReviews(String searchType, String searchKeyword, Pageable pageable) {
        Map<String, Object> params = new HashMap<>();
        if (searchType != null) {
            params.put("searchType", searchType);
        }
        if (searchKeyword != null) {
            params.put("searchKeyword", searchKeyword);
        }

        RowBounds rowBounds = new RowBounds((int) pageable.getOffset(), pageable.getPageSize());

        return sqlSession.selectList(namespace + ".selectReviews", params, rowBounds);
    }

    @Override
    public ReviewVO addReview(ReviewVO review) {
        sqlSession.insert(namespace + ".insertReview", review);
        return review;
    }

    @Override
    public ReviewVO updateReview(ReviewVO review) {
        sqlSession.update(namespace + ".updateReview", review);
        return review;
    }

    @Override
    public void deleteReview(Long reviewId) {
        sqlSession.delete(namespace + ".deleteReview", reviewId);
    }

    @Override
    public ReviewVO getReviewById(Long reviewId) {
        return sqlSession.selectOne(namespace + ".selectReviewById", reviewId);
    }

    @Override
    public int countReviews(String searchType, String searchKeyword) {
        Map<String, Object> params = new HashMap<>();
        if (searchType != null) {
            params.put("searchType", searchType);
        }
        if (searchKeyword != null) {
            params.put("searchKeyword", searchKeyword);
        }
        return sqlSession.selectOne(namespace + ".countReviews", params);
    }
}

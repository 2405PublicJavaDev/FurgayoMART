package com.mart.boot.review.service;

import com.mart.boot.review.model.ReviewVO;

import java.util.List;

public interface ReviewService {

    // 모든 리뷰 조회
    List<ReviewVO> getAllReviews();

    // 검색 조건에 따른 리뷰 조회
    List<ReviewVO> getReviews(String searchType, String searchKeyword);

    // 리뷰 추가
    ReviewVO addReview(ReviewVO review);

    // 리뷰 수정
    ReviewVO updateReview(Long reviewId, ReviewVO review);

    // 리뷰 삭제
    void deleteReview(Long reviewId);

    // 리뷰에 대한 답글 추가
    ReviewVO addReply(Long reviewId, String reContent);

    // 특정 리뷰 조회
    ReviewVO getReviewById(Long reviewId);

    // 검색 조건에 따른 리뷰 개수 조회
    int countReviews(String searchType, String searchKeyword);
}

package com.mart.boot.review.store;

import com.mart.boot.review.model.ReviewVO;

import java.util.List;

public interface ReviewStore {
    List<ReviewVO> getAllReviews(); // 모든 리뷰 조회
    List<ReviewVO> getReviews(String searchType, String searchKeyword); // 검색 조건에 따른 리뷰 조회
    ReviewVO addReview(ReviewVO review); // 리뷰 추가
    ReviewVO updateReview(ReviewVO review); // 리뷰 업데이트
    void deleteReview(Long reviewId); // 리뷰 삭제
    ReviewVO getReviewById(Long reviewId); // 리뷰 ID로 조회
    int countReviews(String searchType, String searchKeyword); // 검색 조건에 따른 리뷰 개수 조회
}

package com.mart.boot.review.service;

import com.mart.boot.review.model.ReviewVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    List<ReviewVO> getAllReviews();

    Page<ReviewVO> getReviews(String searchType, String searchKeyword, Pageable pageable);

    ReviewVO addReview(ReviewVO review);

    ReviewVO updateReview(Long reviewId, ReviewVO review);

    void deleteReview(Long reviewId);

    ReviewVO addReply(Long reviewId, String reContent);

    ReviewVO getReviewById(Long reviewId);

    int countReviews(String searchType, String searchKeyword);

    void addReplyToReview(Long reviewId, String replyContent);
}

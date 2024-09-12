package com.mart.boot.review.store;

import com.mart.boot.review.model.ReviewVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewStore {

    List<ReviewVO> getAllReviews();

    List<ReviewVO> getReviews(String searchType, String searchKeyword, Pageable pageable);

    ReviewVO addReview(ReviewVO review);

    ReviewVO updateReview(ReviewVO review);

    void deleteReview(Long reviewId);

    ReviewVO getReviewById(Long reviewId);

    int countReviews(String searchType, String searchKeyword);
}

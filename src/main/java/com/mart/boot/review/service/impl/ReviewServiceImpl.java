package com.mart.boot.review.service.impl;

import com.mart.boot.review.model.ReviewVO;
import com.mart.boot.review.service.ReviewService;
import com.mart.boot.review.store.ReviewStore;
import com.mart.boot.review.store.ReviewFileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewStore reviewStore;

    @Autowired
    private ReviewFileStore reviewFileStore;

    @Override
    public List<ReviewVO> getAllReviews() {
        return reviewStore.getAllReviews();
    }

    @Override
    public Page<ReviewVO> getReviews(String searchType, String searchKeyword, Pageable pageable) {
        List<ReviewVO> reviews = reviewStore.getReviews(searchType, searchKeyword, pageable);
        int total = reviewStore.countReviews(searchType, searchKeyword);
        return new PageImpl<>(reviews, pageable, total);
    }

    @Override
    @Transactional
    public ReviewVO addReview(ReviewVO review) {
        review.setReviewDate(new Date());
        return reviewStore.addReview(review);
    }

    @Override
    public ReviewVO updateReview(Long reviewId, ReviewVO review) {
        review.setReviewId(reviewId);
        review.setReModify(new Date());
        return reviewStore.updateReview(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewStore.deleteReview(reviewId);
    }

    @Override
    public ReviewVO addReply(Long reviewId, String reContent) {
        ReviewVO review = reviewStore.getReviewById(reviewId);
        if (review != null) {
            review.setReContent(review.getReContent() + "\n\n관리자 답변: " + reContent);
            review.setReModify(new Date());
            return reviewStore.updateReview(review);
        }
        return null;
    }

    @Override
    public ReviewVO getReviewById(Long reviewId) {
        return reviewStore.getReviewById(reviewId);
    }

    @Override
    public int countReviews(String searchType, String searchKeyword) {
        return reviewStore.countReviews(searchType, searchKeyword);
    }

    @Override
    public void addReplyToReview(Long reviewId, String replyContent) {
        ReviewVO review = reviewStore.getReviewById(reviewId);
        if (review != null) {
            review.setReContent(review.getReContent() + "\n\n관리자 답변: " + replyContent);
            review.setReModify(new Date());
            reviewStore.updateReview(review);
        }
    }
}

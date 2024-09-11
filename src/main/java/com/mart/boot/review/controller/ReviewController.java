package com.mart.boot.review.controller;

import com.mart.boot.review.model.ReviewVO;
import com.mart.boot.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewVO>> getReviews(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        List<ReviewVO> reviews = reviewService.getReviews(searchType, searchKeyword);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<ReviewVO> addReview(@ModelAttribute ReviewVO review,
                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                review.setAttachedFile(file);
            }
            if (review.getWriterId() == null || review.getWriterId().isEmpty()) {
                review.setWriterId("temp_user");
            }
            if (review.getUserId() == null || review.getUserId().isEmpty()) {
                review.setUserId("temp_user");
            }
            if (review.getPName() == null || review.getPName().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            ReviewVO savedReview = reviewService.addReview(review);
            return ResponseEntity.ok(savedReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewVO> getReviewById(@PathVariable Long reviewId) {
        ReviewVO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/{reviewId}/reply")
    public ResponseEntity<ReviewVO> addReply(@PathVariable Long reviewId, @RequestBody String replyContent) {
        ReviewVO updatedReview = reviewService.addReply(reviewId, replyContent);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewVO> updateReview(@PathVariable Long reviewId, @RequestBody ReviewVO review) {
        ReviewVO updatedReview = reviewService.updateReview(reviewId, review);
        return ResponseEntity.ok(updatedReview);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countReviews(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        int count = reviewService.countReviews(searchType, searchKeyword);
        return ResponseEntity.ok(count);
    }
}

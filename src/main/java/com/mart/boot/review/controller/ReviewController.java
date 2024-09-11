package com.mart.boot.review.controller;

import com.mart.boot.review.model.ReviewVO;
import com.mart.boot.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 리뷰 목록 가져오기 (페이지네이션 추가)
    @GetMapping
    public ResponseEntity<Page<ReviewVO>> getReviews(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewVO> reviews = reviewService.getReviews(searchType, searchKeyword, pageable);
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 작성하기
    @PostMapping
    public ResponseEntity<ReviewVO> addReview(@ModelAttribute ReviewVO review,
                                              @RequestPart(value = "attachedFile", required = false) MultipartFile file) {
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
            ReviewVO savedReview = reviewService.addReview(review);
            return ResponseEntity.ok(savedReview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 리뷰 가져오기
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewVO> getReviewById(@PathVariable Long reviewId) {
        ReviewVO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    // 리뷰에 답글 추가하기
    @PostMapping("/{reviewId}/reply")
    public ResponseEntity<Void> addReply(@PathVariable Long reviewId, @RequestBody String replyContent) {
        reviewService.addReplyToReview(reviewId, replyContent);
        return ResponseEntity.ok().build();
    }

    // 리뷰 삭제하기
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    // 선택한 리뷰들 삭제하기
    @PostMapping("/bulk-delete")
    public ResponseEntity<Void> bulkDeleteReviews(@RequestBody Map<String, List<Long>> request) {
        List<Long> reviewIds = request.get("reviewIds");
        if (reviewIds != null) {
            for (Long reviewId : reviewIds) {
                reviewService.deleteReview(reviewId);
            }
        }
        return ResponseEntity.ok().build();
    }

    // 리뷰 개수 가져오기 (검색)
    @GetMapping("/count")
    public ResponseEntity<Integer> countReviews(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        int count = reviewService.countReviews(searchType, searchKeyword);
        return ResponseEntity.ok(count);
    }
}
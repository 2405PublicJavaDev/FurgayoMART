package com.mart.boot.review.service.impl;

import com.mart.boot.review.model.ReviewVO;
import com.mart.boot.review.service.ReviewService;
import com.mart.boot.review.store.ReviewStore;
import com.mart.boot.review.store.ReviewFileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public List<ReviewVO> getReviews(String searchType, String searchKeyword) {
        return reviewStore.getReviews(searchType, searchKeyword);
    }

    @Override
    @Transactional
    public ReviewVO addReview(ReviewVO review) {
        review.setReviewDate(new Date()); // 리뷰 작성 날짜 설정
        ReviewVO savedReview = reviewStore.addReview(review); // 리뷰 저장

        // 첨부 파일이 있을 경우 파일 저장 로직 실행
        if (review.getAttachedFile() != null && !review.getAttachedFile().isEmpty()) {
            try {
                String fileName = saveFile(review.getAttachedFile()); // 파일 저장
                reviewFileStore.addReviewFile(savedReview.getReviewId(), fileName); // 파일 정보 DB 저장
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 저장 실패 시 예외 처리 가능
            }
        }

        return savedReview; // 저장된 리뷰 반환
    }

    @Override
    public ReviewVO updateReview(Long reviewId, ReviewVO review) {
        review.setReviewId(reviewId); // 리뷰 ID 설정
        review.setReModify(new Date()); // 수정 날짜 설정
        return reviewStore.updateReview(review); // 리뷰 업데이트
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewStore.deleteReview(reviewId); // 리뷰 삭제
    }

    @Override
    public ReviewVO addReply(Long reviewId, String reContent) {
        ReviewVO review = reviewStore.getReviewById(reviewId); // 리뷰 조회
        if (review != null) {
            // 기존 리뷰 내용에 답변 추가
            String updatedContent = review.getReContent() + "\n\n관리자 답변: " + reContent;
            review.setReContent(updatedContent); // 수정된 내용 설정
            review.setReModify(new Date()); // 수정 날짜 설정
            return reviewStore.updateReview(review); // 리뷰 업데이트
        }
        return null; // 리뷰가 없을 경우 null 반환
    }

    @Override
    public ReviewVO getReviewById(Long reviewId) {
        return reviewStore.getReviewById(reviewId); // 리뷰 ID로 조회
    }

    @Override
    public int countReviews(String searchType, String searchKeyword) {
        return reviewStore.countReviews(searchType, searchKeyword); // 검색 조건에 따른 리뷰 개수 조회
    }

    // 파일 저장 메서드
    private String saveFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename(); // 원본 파일 이름 가져오기
        String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName; // UUID를 이용한 저장 파일 이름 생성
        String uploadPath = "C:/upload/"; // 실제 저장 경로 설정 (필요 시 변경)

        File saveFile = new File(uploadPath, storedFileName); // 저장할 파일 객체 생성
        file.transferTo(saveFile); // 파일 저장

        return storedFileName; // 저장된 파일 이름 반환
    }
}

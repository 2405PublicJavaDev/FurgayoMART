package com.mart.boot.notice.controller;

import com.mart.boot.notice.model.NoticeVO;
import com.mart.boot.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    // 공지사항 목록 가져오기 (페이징 처리 추가)
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getNoticeList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size) {
        try {
            List<NoticeVO> notices = noticeService.selectNoticesWithPaging(page, size);
            int total = noticeService.getTotalNoticeCount();
            int totalPages = (int) Math.ceil((double) total / size);

            Map<String, Object> response = new HashMap<>();
            response.put("notices", notices);
            response.put("currentPage", page);
            response.put("totalItems", total);
            response.put("totalPages", totalPages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching notice list", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 공지사항 등록
    @PostMapping("/create")
    public ResponseEntity<String> createNotice(@RequestBody NoticeVO noticeVO) {
        try {
            logger.info("Received notice: {}", noticeVO);

            if (noticeVO.getNoticeWriter() == null || noticeVO.getNoticeWriter().isEmpty()) {
                noticeVO.setNoticeWriter("임시관리자");
            }
            noticeVO.setUserId("admin");
            noticeVO.setNoticeDate(new Timestamp(System.currentTimeMillis()));
            noticeVO.setNoticeModify(new Timestamp(System.currentTimeMillis()));
            noticeVO.setNoticePinned(0);

            int result = noticeService.insertNotice(noticeVO);
            if (result > 0) {
                return new ResponseEntity<>("공지사항이 성공적으로 등록되었습니다.", HttpStatus.CREATED);
            } else {
                logger.warn("Notice insertion failed");
                return new ResponseEntity<>("공지사항 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating notice", e);
            return new ResponseEntity<>("서버 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 공지사항 수정
    @PutMapping("/update/{noticeNo}")
    public ResponseEntity<String> updateNotice(@PathVariable("noticeNo") Integer noticeNo, @RequestBody NoticeVO noticeVO) {
        try {
            NoticeVO existingNotice = noticeService.selectNoticeById(noticeNo);
            if (existingNotice == null) {
                return new ResponseEntity<>("해당 공지사항을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }

            if (noticeVO.getNoticeTitle() != null && !noticeVO.getNoticeTitle().isEmpty()) {
                existingNotice.setNoticeTitle(noticeVO.getNoticeTitle());
            }

            if (noticeVO.getNoticeContent() != null && !noticeVO.getNoticeContent().isEmpty()) {
                existingNotice.setNoticeContent(noticeVO.getNoticeContent());
            }

            existingNotice.setNoticeModify(new Timestamp(System.currentTimeMillis()));

            int result = noticeService.updateNotice(existingNotice);
            if (result > 0) {
                return new ResponseEntity<>("공지사항이 성공적으로 수정되었습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("공지사항 수정에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating notice", e);
            return new ResponseEntity<>("서버 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 공지사항 삭제
    @DeleteMapping("/delete/{noticeNo}")
    public ResponseEntity<String> deleteNotice(@PathVariable("noticeNo") Integer noticeNo) {
        try {
            int result = noticeService.deleteNotice(noticeNo);
            if (result > 0) {
                return new ResponseEntity<>("공지사항이 성공적으로 삭제되었습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("공지사항 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting notice", e);
            return new ResponseEntity<>("서버 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

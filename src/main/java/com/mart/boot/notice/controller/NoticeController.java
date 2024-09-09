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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin(origins = "*")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/pinned")
    public ResponseEntity<List<NoticeVO>> getPinnedNotices() {
        try {
            List<NoticeVO> pinnedNotices = noticeService.selectPinnedNotices();
            return new ResponseEntity<>(pinnedNotices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching pinned notices", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getNoticeList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword) {
        try {
            logger.info("Fetching notice list for page {} with size {}", page, size);

            int totalNotices = noticeService.getTotalNoticeCount(searchType, searchKeyword);
            List<NoticeVO> allNotices = noticeService.selectAllNotices(searchType, searchKeyword);

            int offset = (page - 1) * size;
            List<NoticeVO> pagedNotices = allNotices.stream()
                    .skip(offset)
                    .limit(size)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("notices", pagedNotices);
            response.put("currentPage", page);
            response.put("totalPages", (int) Math.ceil((double) totalNotices / size));
            response.put("totalNotices", totalNotices);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching notice list", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNotice(@RequestBody NoticeVO noticeVO) {
        try {
            logger.info("Received notice for creation: {}", noticeVO);

            if (noticeVO.getNoticeWriter() == null || noticeVO.getNoticeWriter().isEmpty()) {
                noticeVO.setNoticeWriter("임시관리자");
            }
            noticeVO.setUserId("admin");
            noticeVO.setNoticeDate(new Timestamp(System.currentTimeMillis()));
            noticeVO.setNoticeModify(new Timestamp(System.currentTimeMillis()));
            noticeVO.setNoticePinned(0);

            logger.info("Prepared notice for insertion: {}", noticeVO);

            int result = noticeService.insertNotice(noticeVO);
            logger.info("Insert result: {}", result);

            if (result > 0) {
                return new ResponseEntity<>("공지사항이 성공적으로 등록되었습니다.", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("공지사항 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error occurred while creating notice", e);
            return new ResponseEntity<>("서버 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{noticeNo}")
    public ResponseEntity<String> updateNotice(@PathVariable("noticeNo") Integer noticeNo, @RequestBody NoticeVO noticeVO) {
        try {
            logger.info("Updating notice with ID: {}", noticeNo);
            NoticeVO existingNotice = noticeService.selectNoticeById(noticeNo);
            if (existingNotice == null) {
                return new ResponseEntity<>("해당 공지사항을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }
            // 제목, 내용, 고정 상태 업데이트
            if (noticeVO.getNoticeTitle() != null) {
                existingNotice.setNoticeTitle(noticeVO.getNoticeTitle());
            }
            if (noticeVO.getNoticeContent() != null) {
                existingNotice.setNoticeContent(noticeVO.getNoticeContent());
            }
            if (noticeVO.getNoticePinned() != null) {
                existingNotice.setNoticePinned(noticeVO.getNoticePinned());
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

    @DeleteMapping("/delete/{noticeNo}")
    public ResponseEntity<String> deleteNotice(@PathVariable("noticeNo") Integer noticeNo) {
        try {
            logger.info("Deleting notice with ID: {}", noticeNo);
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
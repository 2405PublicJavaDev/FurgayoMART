package com.mart.boot.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/admin")
    public String showAdminMain() {
        return "admin/adminMain";
    }

    @GetMapping("/inquiry")
    public String showInquiry() {
        return "inquiry/inquiry";
    }

    @GetMapping("/introduce")
    public String showIntroduce() {
        return "introduce/introduce";
    }

    @GetMapping("/notice/admin")
    public String showNoticeAdmin() {
        return "notice/noticeAdmin";
    }

    @GetMapping("/notice/user")
    public String showNoticeUser() {
        return "notice/noticeUser";
    }

    @GetMapping("/policy")
    public String showPolicy() {
        return "policy/policy";
    }

    @GetMapping("/review/admin")
    public String showReviewAdmin() {
        return "review/reviewAdmin";
    }

    @GetMapping("/review/user")
    public String showReviewUser() {
        return "review/reviewUser";
    }
}
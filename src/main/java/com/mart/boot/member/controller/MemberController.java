package com.mart.boot.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.mart.boot.member.emailconfig.EmailService;
import com.mart.boot.member.model.service.MemberService;
import com.mart.boot.member.model.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService mService;

    // 회원가입 페이지
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "member/register";
    }

    // 회원가입 로직 수행
    @PostMapping("/register")
    public String insertMember(@ModelAttribute MemberVO member, 
    							Model model,
    							RedirectAttributes redirectAttributes) {
        try {
            // 비밀번호 확인 로직
//            if (!member.getMemberPw().equals(member.getMemberPwConfirm())) {
//                redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
//                return "redirect:/";
//            }

            // 관리자 여부 기본값 설정
//            if (member.getAdminYn() == null) {
//                member.setAdminYn("N");
//            }

            int result = mService.insertMember(member);
            if (result > 0) {
                return "redirect:/";  // 메인 페이지로 리다이렉트
            } else {
//                redirectAttributes.addFlashAttribute("error", "회원가입이 완료되지 않았습니다.");
                model.addAttribute("error", "회원가입이 완료되지 않았습니다.");
                return "common/error";
            }
        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
        	model.addAttribute("error", e.getMessage());
            return "common/error";
        }
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String showLoginForm(Model model) {
    	return "member/login";
    }
   
    // 로그인 로직 수행
    @PostMapping("/login")
    public String checkLogin(Model model
    		, @RequestParam("memberPhone") String memberPhone
    		, @RequestParam("memberPw") String memberPw
    		, HttpSession session) {
    	try {
    		MemberVO member = new MemberVO();
    		member.setMemberPhone(memberPhone);
    		member.setMemberPw(memberPw);
    		
    		member = mService.checkMemberLogin(member);
    		if(member != null) {
    			session.setAttribute("memberPhone", member.getMemberPhone());
    			session.setAttribute("memberName", member.getMemberName());
    			return "redirect:/";
    		} else {
    			model.addAttribute("error", "로그인이 완료되지 않았습니다.");
    			return "common/error";
    		}
    	} catch (Exception e) {
    		model.addAttribute("error", e.getMessage());
    		return "common/error";
    	}
    }
    
    @GetMapping("/logout")
    public String memberLogout(HttpSession session
    		, RedirectAttributes redirectAttributes) {
    	try {
    		if (session != null) {
    			String memberPhone = (String) session.getAttribute("memberPhone");
    			if (memberPhone != null) {
    				logger.info("User logged out: {}", memberPhone);
    			}
    			session.invalidate();
    			redirectAttributes.addFlashAttribute("msg", "로그아웃되었습니다.");
    			return "redirect:/member/login";
    		}
    	} catch (Exception e) {
    		logger.error("로그아웃 처리 중 오류 발생", e);
    		redirectAttributes.addFlashAttribute("error", "로그아웃 처리 중 오류가 발생했습니다.");
    	}
    	return "redirect:/";
    }
    
    // 마이페이지 페이지
    @GetMapping("/mypage")
    public String showMypage(Model model
    		, HttpSession session
    		, RedirectAttributes redirectAttributes ) {
    	String memberPhone = (String) session.getAttribute("memberPhone");
    	
    	if (memberPhone == null) {
    		redirectAttributes.addFlashAttribute("msg", "로그인이 필요한 서비스입니다.");
    		return "redirect:/member/login";
    	}
    	
    	try {
    		MemberVO member = mService.selectOneByPhone(memberPhone);
    		if (member != null) {
    			model.addAttribute("member", member);
    			model.addAttribute("isAdmin", "Y".equals(member.getAdminYn()));
    			return "member/mypage";
    		} else {
    			model.addAttribute("msg", "회원 정보가 존재하지 않습니다.");
    			return "redirect:/";
    		}
    	} catch (Exception e) {
    		logger.error("마이페이지 로딩 중 오류 발생", e);
    		redirectAttributes.addFlashAttribute("msg", "서비스 처리 중 오류가 발생했습니다.");
    		return "redirect:/";
    	}
    }

    // 회원정보 수정 페이지
    @GetMapping("/update")
    public String showUpdateForm(Model model
    		, HttpSession session
    		, RedirectAttributes redirectAttributes) {
    	
    	String memberPhone = (String) session.getAttribute("memberPhone");
    	if (memberPhone == null) {
    		redirectAttributes.addFlashAttribute("msg", "로그인이 필요한 서비스입니다.");
    		return "redirect:/member/login";
    	}
    	try {
    		MemberVO member = mService.selectOneByPhone(memberPhone);
    		if (member != null) {
    			model.addAttribute("member", member);
    			return "member/update";
    		} else {
    			redirectAttributes.addFlashAttribute("msg", "회원 정보가 존재하지 않습니다.");
    			return "redirect:/";
    		}
    	} catch (Exception e) {
    		logger.error("회원 정보 조회 중 오류 발생", e);
    		redirectAttributes.addFlashAttribute("msg", "서비스 처리 중 오류가 발생했습니다.");
    		return "redirect:/";
    	}
    }
    
    // 회원정보 수정 로직 수행
    @PostMapping("/update")
    public String updateMember(@ModelAttribute MemberVO member
    		, HttpSession session
    		, RedirectAttributes redirectAttributes) {
    	String memberPhone = (String) session.getAttribute("memberPhone");
    	if (memberPhone == null || !memberPhone.equals(member.getMemberPhone())) {
    		redirectAttributes.addFlashAttribute("msg", "잘못된 접근입니다.");
    		return "redirect:/";
    	}
    	try {
    		int result = mService.updateMember(member);
    		if(result > 0) {
    			redirectAttributes.addFlashAttribute("msg", "회원 정보가 성공적으로 수정되었습니다.");
    			return "redirect:/member/mypage";
    		} else {
    			redirectAttributes.addFlashAttribute("msg", "회원 정보 수정에 실패했습니다.");
    			return "redirect:/member/update";
    		}
    	} catch (Exception e) {
    		logger.error("회원 정보 수정 중 오류 발생", e);
    		redirectAttributes.addFlashAttribute("msg", "서비스 처리 중 오류가 발생했습니다.");
    		return "redirect:/member/update";
    	}
    }
    
    // 회원탈퇴 로직 수행
    @PostMapping("/delete")
    public String deleteMember(HttpSession session
    		, RedirectAttributes redirectAttributes) {
    	String memberPhone = (String) session.getAttribute("memberPhone");
    	
    	if (memberPhone == null) {
    		redirectAttributes.addFlashAttribute("error", "로그인이 필요한 서비스입니다.");
    		return "redirect:/member/login";
    	}
    	
    	try {
			int result = mService.deleteMember(memberPhone);
			if (result > 0) {
				logger.info("회원 탈퇴 성공: {} ", memberPhone);
				session.invalidate();
				redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
				return "redirect:/member/logout";
			} else {
				logger.info("회원 탈퇴 실패 : {}", memberPhone);
				redirectAttributes.addFlashAttribute("error", "회원탈퇴가 완료되지 않았습니다.");
				return "redirect:/member/update";
			}
    	} catch (Exception e) {
    		logger.info("회원 탈퇴 처리 중 오류 발생", e);
    		redirectAttributes.addFlashAttribute("error", "회원탈퇴 처리 중 오류가 발생했습니다.");
    		return "redirect:/member/update";
    	}
    }
    
    // 아이디 찾기 페이지
    @GetMapping("/find-id")
    public String showFindIdForm() {
    	return "member/find-id";
    }
    
    // 아이디 찾기 로직 수행
    @PostMapping("/find-id")
    public String findId(@RequestParam String name,
                         @RequestParam String email,
                         Model model) {
        try {
            String foundId = mService.findIdByEmailAndName(email, name);
            if (foundId != null) {
                model.addAttribute("foundId", foundId);
                return "member/id-result";
            } else {
                model.addAttribute("error", "일치하는 회원 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "아이디 찾기 중 오류가 발생했습니다.");
        }
        return "member/find-id";
    }
}
//	// 이메일 인증코드 발송
//	@PostMapping("/send-verification")
//	@ResponseBody
//	public String sendVerificationEmail(@RequestParam String email) {
//		emailService.sendVerificationEmail(email);
//		return "인증 이메일이 발송되었습니다.";
//	}
//	
//	// 이메일 인증코드 유효성 검사
//	@PostMapping("/verify-email")
//	@ResponseBody
//	public String verifyEmail(@RequestParam String email, @RequestParam String code) {
//		boolean isVerified = emailService.verifyEmail(email, code);
//		return isVerified ? "이메일이 성공적으로 인증되었습니다." : "인증 코드가 올바르지 않습니다.";
//	}
//}

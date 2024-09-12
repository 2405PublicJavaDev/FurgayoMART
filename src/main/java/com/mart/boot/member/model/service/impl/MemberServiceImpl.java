package com.mart.boot.member.model.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.boot.member.emailconfig.EmailConfig;
import com.mart.boot.member.emailconfig.EmailService;
import com.mart.boot.member.model.mapper.MemberMapper;
import com.mart.boot.member.model.service.MemberService;
import com.mart.boot.member.model.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
//	private final EmailService emailService;
	
//	private Map<String, String> verificationCodes = new HashMap<>();
	
	@Autowired
	private MemberMapper mStore;
	
	@Autowired
	private EmailService eService;


	
    @Override
    public int insertMember(MemberVO member) throws Exception {
        member.setSmsAgreementYn(member.getSmsAgreementYn() != null ? member.getSmsAgreementYn() : "N");
        member.setEmailAgreementYn(member.getEmailAgreementYn() != null ? member.getEmailAgreementYn() : "N");

        int result = mStore.insertMember(member);
        if (result <= 0) {
            throw new RuntimeException("회원 등록에 실패했습니다.");
        }
        return result;
    }

	@Override
	public MemberVO checkMemberLogin(MemberVO member) throws Exception {
		MemberVO loggedInMember = mStore.checkMemberLogin(member);
		if (loggedInMember != null) {
			loggedInMember.setMemberPw(null);
		}
		return loggedInMember;
	}


	@Override
	public MemberVO selectOneByNo(Long memberNo) {
		MemberVO member = mStore.selectOneByNo(memberNo);
		return member;
	}


	@Override
	public int updateMember(MemberVO member) {
		int result = mStore.updateMember(member);
		return result;
	}


	@Override
	public int deleteMember(String memberPhone) {
		int result = mStore.deleteMember(memberPhone);
		return result;
	}


	@Override
	public String findIdByEmailAndName(String email, String name) throws Exception{
		MemberVO member = mStore.selectMemberByEmailAndName(email, name);
		if (member != null) {
//			emailConfig.sendIdRevoveryEmail(email, member.getMemberPhone());
			return member.getMemberPhone();
		}
		return null;
	}
	
	@Override
	public String findPwByEmailAndPhoneAndName(String email, String phone, String name) {
		MemberVO member = mStore.selectPwMemberByEmailAndPhoneAndName(email, phone, name);
		if (member != null) {
			String tempPassword = generateTempPassword();
			member.setMemberPw(tempPassword);
			mStore.updateMemberPassword(member);
			eService.sendPasswordResetEmail(member.getMemberEmail(), tempPassword);
//			emailConfig.sendPasswordResetEmail(member.getMemberEmail(), tempPassword);
			return tempPassword;
		}
		return null;
	}


	private String generateTempPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
//	@Override
//	public String sendVerificationEmail(String email) {
//		String verificationCode = generateVerificationCode();
//		verificationCodes.put(email, verificationCode);
////		emailService.sendVerificationEmail(email, verificationCode);
//		return verificationCode;
//	}
//	
//	@Override
//	public boolean verifyEmail(String email, String code) {
//		String storedCode = verificationCodes.get(email);
//		return storedCode != null && storedCode.equals(code);
//	}
//	
//	// 6자리 랜덤 인증 코드를 생성
//	private String generateVerificationCode() {
//		Random random = new Random();
//		return String.format("%06d", random.nextInt(999999));
//	}
}

package com.mart.boot.member.model.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mart.boot.member.model.mapper.MemberMapper;
import com.mart.boot.member.model.service.MemberService;
import com.mart.boot.member.model.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
//	private final EmailService emailService;
	
//	private Map<String, String> verificationCodes = new HashMap<>();
	
	@Autowired
	private MemberMapper mStore;

	
	@Override
	public int insertMember(MemberVO member) throws Exception {
		int result = mStore.insertMember(member);
		return result;
	}


	@Override
	public MemberVO checkMemberLogin(MemberVO member) {
		MemberVO result = mStore.checkMemberLogin(member);
		return result;
	}


	@Override
	public MemberVO selectOneByPhone(String memberPhone) {
		MemberVO member = mStore.selectOneByPhone(memberPhone);
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
	public String findIdByEmailAndName(String email, String name) {
		MemberVO member = mStore.selectMemberByEmailAndName(email, name);
		return member != null ? member.getMemberPhone() : null;
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

package com.mart.boot.member.model.service;

import com.mart.boot.member.model.vo.MemberVO;

public interface MemberService {
	
	/**
	 * 신규회원 등록
	 * @param member
	 * @return 회원등록 성공여부 (성공 시 true, 실패 시 false)
	 */
	int insertMember(MemberVO member) throws Exception;

	/**
	 * 로그인
	 * @param member
	 * @return MemberVO
	 */
	MemberVO checkMemberLogin(MemberVO member);

	/**
	 * 마이페이지
	 * @param memberPhone
	 * @return MemberVO
	 */
	MemberVO selectOneByPhone(String memberPhone);

	/**
	 * 회원정보 수정
	 * @param member
	 * @return int
	 */
	int updateMember(MemberVO member);

	/**
	 * 회원 탈퇴
	 * @param memberPhone
	 * @return int
	 */
	int deleteMember(String memberPhone);

	/**
	 * 아이디 찾기
	 * @param email
	 * @param name
	 * @return String
	 */
	String findIdByEmailAndName(String email, String name) throws Exception;

	/**
	 * 비밀번호 찾기
	 * @param email
	 * @param phone
	 * @param name
	 * @return String
	 */
	String findPwByEmailAndPhoneAndName(String email, String phone, String name);
	
//	/**
//	 * 지정된 이메일 주소로 인증 코드를 발송
//	 * @param email
//	 * @return String (생성된 인증코드)
//	 */
//	String sendVerificationEmail(String email);
//	
//	/**
//	 * 이메일 주소와 인증 코드의 유효성을 검증
//	 * @param email 검증할 이메일 주소
//	 * @param code 사용자가 입력한 인증코드
//	 * @return 인증 성공여부(성공 시 true, 실패 시 false)
//	 */
//	boolean verifyEmail(String email, String code);
}

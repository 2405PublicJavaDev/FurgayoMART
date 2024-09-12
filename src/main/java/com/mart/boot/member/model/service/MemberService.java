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
	MemberVO checkMemberLogin(MemberVO member) throws Exception;

	/**
	 * 마이페이지
	 * @param memberNo
	 * @return MemberVO
	 */
	MemberVO selectOneByNo(Long memberNo);

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
}

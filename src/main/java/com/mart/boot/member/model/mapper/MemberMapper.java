package com.mart.boot.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mart.boot.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	/**
	 * 회원가입 정보 입력
	 * @param member
	 * @return int
	 */
	int insertMember(MemberVO member);

	/**
	 * 회원번호로 로그인 정보확인
	 * @param member
	 * @return
	 */
	MemberVO checkMemberLogin(MemberVO member);

	/**
	 * 회원의 마이페이지 조회 
	 * @param memberNo
	 * @return
	 */
	MemberVO selectOneByNo(Long memberNo);

	/**
	 * 회원정보 수정
	 * @param member
	 * @return
	 */
	int updateMember(MemberVO member);

	/**
	 * 회원정보 삭제
	 * @param memberPhone
	 * @return
	 */
	int deleteMember(String memberPhone);

	/**
	 * 아이디 찾기
	 * @param memberEmail
	 * @param memberName
	 * @return
	 */
	MemberVO selectMemberByEmailAndName(@Param("memberEmail") String memberEmail, @Param("memberName") String memberName);

	/**
	 * 비밀번호 찾기
	 * @param email
	 * @param phone
	 * @param name
	 * @return
	 */
	MemberVO selectPwMemberByEmailAndPhoneAndName(String email, String phone, String name);

	/**
	 * 비밀번호 찾기에서 임시 비밀번호 생성
	 * @param member
	 */
	void updateMemberPassword(MemberVO member);

	/**
	 * 장바구니에서 회원정보 조회
	 * @param memberNo
	 * @return
	 */
	MemberVO selectMemberByNo(Long memberNo);
}

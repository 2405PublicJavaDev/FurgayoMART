package com.mart.boot.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mart.boot.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	int insertMember(MemberVO member);

	MemberVO checkMemberLogin(MemberVO member);

	MemberVO selectOneByPhone(String memberPhone);

	int updateMember(MemberVO member);

	int deleteMember(String memberPhone);

	MemberVO selectMemberByEmailAndName(@Param("memberEmail") String memberEmail, @Param("memberName") String memberName);

	MemberVO selectPwMemberByEmailAndPhoneAndName(String email, String phone, String name);

	void updateMemberPassword(MemberVO member);
	
//	int countByUsername(String memberPhone);
}

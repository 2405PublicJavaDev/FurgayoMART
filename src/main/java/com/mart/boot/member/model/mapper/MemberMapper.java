package com.mart.boot.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mart.boot.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {
	
	int insertMember(MemberVO member);

	MemberVO checkMemberLogin(MemberVO member);

	MemberVO selectOneByPhone(String memberPhone);

	int updateMember(MemberVO member);

	int deleteMember(String memberPhone);

	MemberVO selectMemberByEmailAndName(String email, String name);
	
//	int countByUsername(String memberPhone);
}

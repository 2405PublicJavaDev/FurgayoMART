package com.mart.boot.member.model.store;

import com.mart.boot.member.model.vo.MemberVO;

public interface MemberStore {

//	/**
//	 * 주어진 휴대폰 번호로 회원이 존재하는지 확인
//	 * @param memberPhone 확인할 회원의 휴대폰 번호
//	 * @return boolean 회원 존재여부(존재하면 true, 아니면 false)
//	 */
//	boolean existsByUsername(String memberPhone);

	int insertMember(MemberVO member) throws Exception;
	
}

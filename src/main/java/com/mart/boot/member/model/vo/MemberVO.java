package com.mart.boot.member.model.vo;

public class MemberVO {
    private Long memberNo;          // 회원 고유번호
    private String memberPhone;     // 회원 로그인 아이디(휴대폰번호)
    private String memberPw;		// 회원 비밀번호
    private String memberName;		// 회원 이름
    private String memberEmail;		// 회원 이메일
    private String adminYn = "N";        // 관리자 여부
    
    // 동의 항목 추가
    private String termsAgreementYn;
    private String privacyAgreementYn;
    private String smsAgreementYn;
    private String emailAgreementYn;
    
    // Getters and Setters
	public Long getMemberNo() { 
		return memberNo;
	}
	public void setMemberNo(Long memberNo) { 
		this.memberNo = memberNo;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getAdminYn() {
		return adminYn;
	}
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}
	public String getTermsAgreementYn() {
		return termsAgreementYn;
	}
	public void setTermsAgreementYn(String termsAgreementYn) {
		this.termsAgreementYn = termsAgreementYn;
	}
	public String getPrivacyAgreementYn() {
		return privacyAgreementYn;
	}
	public void setPrivacyAgreementYn(String privacyAgreementYn) {
		this.privacyAgreementYn = privacyAgreementYn;
	}
	public String getSmsAgreementYn() {
		return smsAgreementYn;
	}
	public void setSmsAgreementYn(String smsAgreementYn) {
		this.smsAgreementYn = smsAgreementYn;
	}
	public String getEmailAgreementYn() {
		return emailAgreementYn;
	}
	public void setEmailAgreementYn(String emailAgreementYn) {
		this.emailAgreementYn = emailAgreementYn;
	}
	@Override
	public String toString() {
		return "MemberVO [memberNo=" + memberNo + ", memberPhone=" + memberPhone + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", memberEmail=" + memberEmail + ", adminYn=" + adminYn
				+ ", termsAgreementYn=" + termsAgreementYn + ", privacyAgreementYn=" + privacyAgreementYn
				+ ", smsAgreementYn=" + smsAgreementYn + ", emailAgreementYn=" + emailAgreementYn + "]";
	}
}
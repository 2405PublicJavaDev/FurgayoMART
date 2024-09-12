package com.mart.boot.member.emailconfig;

public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);
	
	void sendIdRecoveryEmail(String memberEmail, String memberPhone);
	
	void sendPasswordResetEmail(String memberEmail, String tempPassword);
}

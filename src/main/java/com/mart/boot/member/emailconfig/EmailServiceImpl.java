package com.mart.boot.member.emailconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("practiceemailcrwong@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}

	@Override
	public void sendIdRecoveryEmail(String memberEmail, String memberPhone) {
		String subject = "아이디(휴대폰 번호) 찾기 결과";
		String message = "귀하의 아이디(휴대폰 번호)는 " + memberPhone + " 입니다.";
		sendSimpleMessage(memberEmail, subject, message);
	}

	@Override
	public void sendPasswordResetEmail(String memberEmail, String tempPassword) {
		String subject = "귀하의 비밀번호 재설정";
		String message = "귀하의 임시 비밀번호는 " + tempPassword + "입니다. 로그인 후 비밀번호를 변경해주세요.";
		sendSimpleMessage(memberEmail, subject, message);
		
	}
	
}


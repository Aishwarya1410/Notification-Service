package com.springboot.notification_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author prana
 *
 */
@Service
public class OtpConsumerService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@KafkaListener(topics= "otp_topic", groupId = "otp_group")
	public void consumerOtp(String message){
		String[] parts = message.split(":");
		String phoneNumber = parts[0];
		String otp = parts[1];
		sendOtpEmail(phoneNumber, otp);
	}
	
	
	public void sendOtpEmail(String to, String otp){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Your OTP code");
		message.setText("Your OTP code is:" + otp);
		System.out.println("Your OTP code is:" + otp);
		//mailSender.send(message);
		
	}
	
	

}

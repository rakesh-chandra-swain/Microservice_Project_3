package com.nt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendVerificationOtpEmail(String userEmail, String otp) throws MessagingException, MailSendException {
    		
    	String subject = "Email Verification OTP";
        String text = "<p>Dear user,</p>"
                    + "<p>Your OTP for email verification is: <strong>" + otp + "</strong></p>"
                    + "<p>Please use this OTP to complete your verification process.</p>"
                    + "<br><p>Regards,<br>Your App Team</p>";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


            helper.setSubject(subject);
            helper.setText(text+otp, true);
            helper.setTo(userEmail);
            javaMailSender.send(mimeMessage);
        } catch (MailException e) {
            throw new MailSendException("Failed to send email");
        }
    }
}


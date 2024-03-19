package org.example.eco.user;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.example.eco.common.Variables.*;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public void sendVerifyCode(String email,int code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_EMAIL);
            message.setTo(email);
            message.setSubject(VERIFY_CODE_MESSAGE);
            message.setText(String.valueOf(code));
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void forgetPassword(String email, String url) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FROM_EMAIL);
            message.setTo(email);
            message.setSubject(VERIFY_CODE_MESSAGE);
            message.setText(url);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.datmt.spring_mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RestController
public class SendMailController {

    @GetMapping("/send-mail")
    public void send() throws MessagingException, UnsupportedEncodingException {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("192.168.1.95");
        sender.setPort(1025);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress("datmt@local.com", "Datmt"));
        helper.setSubject("Hello, world!");
        helper.setTo("test@mail.com");
        helper.setText("Thank you for ordering!");

        sender.send(message);
    }
}

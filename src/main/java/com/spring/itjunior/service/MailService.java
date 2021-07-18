package com.spring.itjunior.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;

@Log4j2
@AllArgsConstructor
@Service
public class MailService {

    private JavaMailSenderImpl mailSender;

    private static final String FROM_ADDRESS = "ziontduo@gmail.com";
    private static final String FROM_TITLE = "<p>ITJunior 비밀번호찾기 인증번호 안내</p>" + "<h1>인증번호 : </h1>";

    public int mailSend(String toEmail, String toName) {
        log.info("mail 서비스 진입..!!");
        Random random = new Random();
        int certificationNum = random.nextInt(888888) + 111111; //111111 ~ 999999까지의 난수

        String FROM_TEXT = "<h1>안녕하세요 ITJunior입니다. 홈페이지를 방문해주셔서 감사합니다.</h1>" +
                            "<br><br>"+
                            toName+"님이 요청하신 비밀번호 찾기 인증번호를 안내해 드립니다."+
                            "<br>" +
                            "아래 번호를 홈페이지 인증번호 입력 란에 입력하시면"+
                            "<br>"+
                            "새 비밀번호를 설정하실 수 있습니다."+
                            "인증 번호는  " + certificationNum + "  입니다." +
                            "<br>" +
                            "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(FROM_ADDRESS);
        message.setSubject(FROM_TITLE);
        message.setText(FROM_TEXT);

        Properties prop = mailSender.getJavaMailProperties();

        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        mailSender.send(message);

        return certificationNum;
    }


}

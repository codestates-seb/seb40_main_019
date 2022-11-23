package com.backend.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${naver.id}")
    private String naverId;

    @Value("${naver.password}")
    private String naverPassword;

    /**
     * 메일 전송 서비스를 위한 JavaMailSender Bean 등록
     *
     * @return JavaMailSender
     */
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // 메인 도메인 서버 주소 => 정확히는 smtp 서버 주소
        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername(naverId);
        javaMailSender.setPassword(naverPassword);
        // 메일 인증서버 포트
        javaMailSender.setPort(465);
        // 메일 인증서버 정보 가져오기
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        // 프로토콜 설정
        properties.setProperty("mail.transport.protocol", "smtp");
        // smtp 인증
        properties.setProperty("mail.smtp.auth", "true");
        // smtp strattles 사용
        properties.setProperty("mail.smtp.starttls.enable", "true");
        // 디버그 사용
        properties.setProperty("mail.debug", "true");
        // ssl 인증 서버는 smtp.naver.com
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        // ssl 사용
        properties.setProperty("mail.smtp.ssl.enable", "true");
        return properties;
    }
}

package com.EventHorizon.EventHorizon.MailSender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderBean{
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587); // use the appropriate port
        mailSender.setUsername("aher70740@gmail.com");
        mailSender.setPassword("fjws bljs kwro sgns");
        java.util.Properties properties = new java.util.Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.timeout", 100000);
        properties.put("mail.smtp.enable", true);
        properties.put("mail.smtp.enable", true);
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}

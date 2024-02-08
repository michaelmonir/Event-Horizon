package com.EventHorizon.EventHorizon.MailSender;

import com.EventHorizon.EventHorizon.DTOs.Notification.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private  JavaMailSender javaMailSender;
    public void sendMail(String to,String subject,String name,String verifyCode){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(
                "Hello\t" + name + "\n\n" + "Thanks for signing up with EventHorizon\n\n" +
                        "To verify your email please use the next code" + "\n\n" + "Verification Code :\t" + verifyCode + "\n\n" + "We look forward to see you in next event\n\n" + "Sincerely,\n" +
                        "EventHorizon Team"
        );
        javaMailSender.send(message);
    }
    public void sendMessageToUser(MessageDto messageDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(messageDto.userEmail);
        message.setSubject(messageDto.subject);
        message.setText(messageDto.body);
        javaMailSender.send(message);
    }
}

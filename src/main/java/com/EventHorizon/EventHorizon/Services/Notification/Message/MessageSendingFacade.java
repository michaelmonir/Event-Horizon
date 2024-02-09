package com.EventHorizon.EventHorizon.Services.Notification.Message;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.MailSender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageSendingFacade {

    @Autowired
    private EmailSenderService emailSenderService;

    public void sendToUsers(GmailMessage message, List<User> users) {
        for (User user : users) {
            emailSenderService.sendMessageToUser(message.createMessageDtoForUser(user));
        }
    }
}

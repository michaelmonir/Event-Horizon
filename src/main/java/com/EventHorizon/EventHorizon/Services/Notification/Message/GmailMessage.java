package com.EventHorizon.EventHorizon.Services.Notification.Message;

import com.EventHorizon.EventHorizon.DTOs.Notification.MessageDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;

import java.util.function.Function;

public class GmailMessage {

    private String subject;
    private Function<User, String> getUserMethod;

    public GmailMessage(String subject, Function<User, String> getUserMethod) {
        this.subject = subject;
        this.getUserMethod = getUserMethod;
    }

    public MessageDto createMessageDtoForUser(User user) {
        return new MessageDto(
                user.getEmail(),
                subject,
                getUserMethod.apply(user)
        );
    }
}

package com.EventHorizon.EventHorizon.Service.Notification.Message;

import com.EventHorizon.EventHorizon.DTOs.Notification.MessageDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Services.Notification.Message.GmailMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

@SpringBootTest
public class GmailMessageTest {

    @Autowired
    private UserCustomCreator userCustomCreator;

    @RepeatedTest(2)
    public void testMessageCreation() {
        Function<User, String> getUserMethod
                = user -> "Hi "
                + user.getFirstName()
                + " " + user.getLastName()
                + " " + user.getRole();
        GmailMessage gmailMessage
                = new GmailMessage("subject1", getUserMethod);
        User user = userCustomCreator.getAndSaveUser(Role.SPONSOR);

        MessageDto expectedMessageDto
                = new MessageDto(user.getEmail()
                , "subject1",
                "Hi "
                        + user.getFirstName() + " " + user.getLastName()
                        + " " + user.getRole());

        Assertions.assertEquals(expectedMessageDto, gmailMessage.createMessageDtoForUser(user));
    }
}

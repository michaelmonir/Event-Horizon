package com.EventHorizon.EventHorizon.security;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ExistingMail;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ExistingUserName;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.security.Service.ProxyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProxyServiceTest {
    @Autowired
    ProxyService proxyService;
    @Autowired
    UserRepositoryService userRepositoryService;

    @Test
    public void mailInSystemTest() {
        assertFalse(proxyService.mailInSystem("ahmedd@gmail.com"));
    }

    @Test
    public void mailIsInSystemTest() {
        Client client = Client.builder().email("ahmed@gmail.com").password("password").userName("ahmed").role(Role.CLIENT).active(1).build();
        userRepositoryService.create(client);
        assertTrue(proxyService.mailInSystem("ahmed@gmail.com"));
    }

    @Test
    public void userNameInSystemTest() {
        assertFalse(proxyService.userNameInSystem("ahmed2"));
    }

    @Test
    public void userNameIsInSystemTest() {
        Client client = Client.builder().email("ahmed2@gmail.com").password("password").userName("ahmed2").role(Role.CLIENT).active(1).build();
        userRepositoryService.create(client);
        assertTrue(proxyService.userNameInSystem("ahmed2"));
    }

    @Test
    public void signUpExistingMailTest() {
        Client client = Client.builder().email("ahmed8@gmail.com").password("password").userName("ahmed8").role(Role.CLIENT).active(1).enable(1).build();
        userRepositoryService.create(client);
        Assertions.assertThrows(
                ExistingMail.class, () -> {
                    UserCreationDto request = UserCreationDto.builder().email("ahmed8@gmail.com").password("password").userName("ahmed8").role("ROLE_CLIENT").build();
                    proxyService.signUp(request);
                }
        );
    }

    @Test
    public void signUpExistingUserNameTest() {
        Client client = Client.builder().email("ahmed9@gmail.com").password("password").userName("ahmed9").role(Role.CLIENT).active(1).build();
        userRepositoryService.create(client);
        Assertions.assertThrows(
                ExistingUserName.class, () -> {
                    UserCreationDto request = UserCreationDto.builder().email("ahmed10@gmail.com").password("password").userName("ahmed9").role("ROLE_CLIENT").build();
                    proxyService.signUp(request);
                }
        );
    }
}

package com.EventHorizon.EventHorizon.Service.User;

import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserCustomCreator userCustomCreator;
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepositoryService userRepositoryService;

    @Test
    public void updateUser() {

    }
}

package com.EventHorizon.EventHorizon.Service.AuthorityServiceTests;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotModeratorOperationsException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.AuthorityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AuthorityServiceTest {
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    AuthorityService authorityService;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Test
    public void deleteUserTestIfSucceed() {
        User information = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.add(information);
        authorityService.deleteUser(information.getId());
        Assertions.assertThrows(
                UserNotFoundException.class, () -> userRepositoryService.getById(information.getId())
        );
    }

    @Test
    public void deleteUserTestIfFail() {
        User information = userCustomCreator.getUser(Role.MODERATOR);
        userRepositoryService.add(information);
        Assertions.assertThrows(
                NotModeratorOperationsException.class, () -> {
                    authorityService.deleteUser(information.getId());
                }
        );
    }

    @Test
    void GetAllUserByRoleTest() {
        User information = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.add(information);
        User information2 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.add(information2);
        User information3 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.add(information3);
        User information4 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.add(information4);

        List<? extends User> list = authorityService.getAllUsersByRole(Role.CLIENT);
        for (User u : list) {
            Client c = (Client) u;
            Assertions.assertEquals(c.getRole(), Role.CLIENT);
        }
        Assertions.assertTrue(list.size() >= 4);
    }
}

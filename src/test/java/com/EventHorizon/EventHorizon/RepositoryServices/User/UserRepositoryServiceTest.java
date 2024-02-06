package com.EventHorizon.EventHorizon.RepositoryServices.User;

import com.EventHorizon.EventHorizon.Entities.User.Client;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.User.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.User.InvalidUserDataException;
import com.EventHorizon.EventHorizon.Exceptions.User.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryServiceTest {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void createSuccessfully() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        Assertions.assertDoesNotThrow(() -> userRepositoryService.create(user));
    }

    @Test
    public void createIdNotZero() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        user.setId(1);
        Assertions.assertThrows(AlreadyFoundException.class, () -> userRepositoryService.create(user));
    }

    @Test
    public void createAdmin() {
        User user = userCustomCreator.getUser(Role.ADMIN);
        Assertions.assertThrows(NotAdminOperationException.class, () -> userRepositoryService.create(user));
    }

    @Test
    public void createSameEmail() {
        User user1 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user1);

        User user2 = userCustomCreator.getUser(Role.CLIENT);
        user2.setEmail(user1.getEmail());

        Assertions.assertThrows(InvalidUserDataException.class, () -> userRepositoryService.create(user2));
    }

    @Test
    public void updateSuccessfully() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user);
        user.setFirstName("newName");
        Assertions.assertDoesNotThrow(() -> userRepositoryService.update(user));
    }

    @Test
    public void deleteUser() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user);
        Assertions.assertDoesNotThrow(() -> userRepositoryService.deleteById(user.getId()));
        Assertions.assertThrows(UserNotFoundException.class, () -> getUserRepositoryService.getById(user.getId()));
    }

    @Test
    public void deleteNotSavedUser() {
        Assertions.assertThrows(UserNotFoundException.class, () -> userRepositoryService.deleteById(100000));
    }

    @Test
    public void checkUserExists() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user);
        Assertions.assertDoesNotThrow(() -> userRepositoryService.checkUserExists(user.getId()));
    }

    @Test
    public void checkUserExistsNotSavedUser() {
        Assertions.assertThrows(UserNotFoundException.class, () -> userRepositoryService.checkUserExists(100000));
    }

    @Test
    public void getRoleAndCheckExistsClient() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user);
        Assertions.assertEquals(Role.CLIENT, userRepositoryService.getRoleAndCheckExists(user.getId()));
    }

    @Test
    public void getRoleAndCheckExistsSponsor() {
        User user = userCustomCreator.getUser(Role.SPONSOR);
        userRepositoryService.create(user);
        Assertions.assertEquals(Role.SPONSOR, userRepositoryService.getRoleAndCheckExists(user.getId()));
    }

    @Test
    public void findAllByRole() {
        User user1 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user1);
        User user2 = userCustomCreator.getUser(Role.CLIENT);
        userRepositoryService.create(user2);
        User user3 = userCustomCreator.getUser(Role.SPONSOR);
        userRepositoryService.create(user3);
        List<Client> clients = (List<Client>) userRepositoryService.findAllByRole(Role.CLIENT);
        Assertions.assertEquals(user1, clients.get(clients.size()- 2));
        Assertions.assertEquals(user2, clients.get(clients.size()- 1));
    }
}








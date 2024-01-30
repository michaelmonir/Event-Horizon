package com.EventHorizon.EventHorizon.Service.AuthorityServiceTests;
import com.EventHorizon.EventHorizon.Entities.UserEntities.*;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotModeratorOperationsException;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.AuthorityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AuthorityServiceTest {
    @Autowired
    InformationCustomCreator informationCreator;
    @Autowired
    AuthorityService authorityService;

    @Autowired
    InformationRepositoryService informationRepositoryService;

    @Test
    public void deleteUserTestIfSucceed() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information);
        authorityService.deleteUser(information.getId());
        Assertions.assertThrows(
                ClientNotFoundException.class, () -> {
                    informationRepositoryService.getUserByInformation(information);
                }
        );
    }

    @Test
    public void deleteUserTestIfFail() {
        Information information = informationCreator.getInformation(Role.MODERATOR);
        informationRepositoryService.add(information);
        Assertions.assertThrows(
                NotModeratorOperationsException.class, () -> {
                    authorityService.deleteUser(information.getId());
                }
        );
    }

    @Test
    void GetAllUserByRoleTest() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information);
        Information information2 = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information2);
        Information information3 = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information3);
        Information information4 = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information4);

        List<? extends User> list = authorityService.getAllUsersByRole(Role.CLIENT);
        for (User u : list) {
            Client c = (Client) u;
            Assertions.assertEquals(c.getInformation().getRole(), Role.CLIENT);
        }
        Assertions.assertTrue(list.size() >= 4);
    }


    @Test
    void getAllUsersByFirstName() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        Information information2 = informationCreator.getInformation(Role.MODERATOR);
        Information information3 = informationCreator.getInformation(Role.ORGANIZER);
        Information information4 = informationCreator.getInformation(Role.SPONSOR);
        String xx = "Messi";
        information.setFirstName(xx);
        information2.setFirstName(xx);
        information3.setFirstName(xx);
        information4.setFirstName(xx);
        informationRepositoryService.add(information);
        informationRepositoryService.add(information2);
        informationRepositoryService.add(information3);
        informationRepositoryService.add(information4);
        List<User> list = authorityService.getAllUsersByFirstName(xx);

        for (User u : list) {
            Information information1=getInformationFromUser(u);
            Assertions.assertEquals(information1.getFirstName(),xx);
        }
    }

    @Test
    void getAllUsersByLastName() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        Information information2 = informationCreator.getInformation(Role.MODERATOR);
        Information information3 = informationCreator.getInformation(Role.ORGANIZER);
        Information information4 = informationCreator.getInformation(Role.SPONSOR);
        String xx = "Messi";
        information.setLastName(xx);
        information2.setLastName(xx);
        information3.setLastName(xx);
        information4.setLastName(xx);
        informationRepositoryService.add(information);
        informationRepositoryService.add(information2);
        informationRepositoryService.add(information3);
        informationRepositoryService.add(information4);
        List<User> list = authorityService.getAllUsersByLastName(xx);
        for (User u : list) {
            Information information1=getInformationFromUser(u);
            Assertions.assertEquals(information1.getLastName(),xx);
        }
    }

    @Test
    void getAllUsersByGender() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        Information information2 = informationCreator.getInformation(Role.MODERATOR);
        Information information3 = informationCreator.getInformation(Role.ORGANIZER);
        Information information4 = informationCreator.getInformation(Role.SPONSOR);
        informationRepositoryService.add(information);
        informationRepositoryService.add(information2);
        informationRepositoryService.add(information3);
        informationRepositoryService.add(information4);
        List<User> list = authorityService.getAllUsersByGender(Gender.MALE);
        for (User u : list) {
            Information information1 = getInformationFromUser(u);
            Assertions.assertEquals(information1.getGender(), Gender.MALE);
        }
    }

    @Test
    void getUserByEmail() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        informationRepositoryService.add(information);
        User user = authorityService.getUserByEmail(information.getEmail());
        Information information1 = getInformationFromUser(user);
        Assertions.assertEquals(information1.getEmail(), information.getEmail());
    }

    public Information getInformationFromUser(User user) {
        if (user.getClass() == Client.class) {
            return ((Client) user).getInformation();
        } else if (user.getClass() == Organizer.class) {
            return ((Organizer) user).getInformation();
        } else if (user.getClass() == Moderator.class) {
            return ((Moderator) user).getInformation();
        } else {
            return ((Sponsor) user).getInformation();
        }
    }
}

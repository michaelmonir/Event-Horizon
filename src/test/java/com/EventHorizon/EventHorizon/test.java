package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class test {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Test
    void test1() {
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepositoryService.add(organizer);
        Organizer organizer1 = (Organizer) userRepositoryService.getById(organizer.getId());
        Assertions.assertEquals(organizer1.getFirstName(), organizer.getFirstName());
    }
    @Test
    void getRoleById(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepositoryService.add(organizer);
        Role role = userRepositoryService.getRole(organizer.getId());
        Assertions.assertEquals(role, organizer.getRole());
    }
    @Test
    void getAllByRole(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);;
        userRepositoryService.add(organizer);
        Organizer organizer1 = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);;
        userRepositoryService.add(organizer1);
        List<?extends User> list = userRepositoryService.findAllByRole(Role.ORGANIZER);
        Assertions.assertEquals(list.get(list.size()-2).getEmail(), organizer.getEmail());
        Assertions.assertEquals(list.get(list.size()-1).getEmail(), organizer1.getEmail());
    }

}


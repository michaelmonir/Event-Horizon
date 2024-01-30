package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedOrganizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UpdatedUserCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class test {

    @Autowired
    private  UpdatedUserRepositoryService updatedUserRepositoryService;
    @Autowired
    private UpdatedUserCustomCreator updatedUserCustomCreator;
    @Test
    void test1() {
        UpdatedOrganizer updatedOrganizer = (UpdatedOrganizer) updatedUserCustomCreator.getUser(Role.ORGANIZER);
        updatedUserRepositoryService.add(updatedOrganizer);
        UpdatedOrganizer updatedOrganizer1 = (UpdatedOrganizer) updatedUserRepositoryService.getById(updatedOrganizer.getId());
        Assertions.assertEquals(updatedOrganizer1.getFirstName(), updatedOrganizer.getFirstName());
    }
    @Test
    void getRoleById(){
        UpdatedOrganizer updatedOrganizer = (UpdatedOrganizer) updatedUserCustomCreator.getUser(Role.ORGANIZER);
        updatedUserRepositoryService.add(updatedOrganizer);
        Role role = updatedUserRepositoryService.getRole(updatedOrganizer.getId());
        Assertions.assertEquals(role,updatedOrganizer.getRole());
    }
    @Test
    void getAllByRole(){
        UpdatedOrganizer updatedOrganizer = (UpdatedOrganizer) updatedUserCustomCreator.getUser(Role.ORGANIZER);;
        updatedUserRepositoryService.add(updatedOrganizer);
        UpdatedOrganizer updatedOrganizer1 = (UpdatedOrganizer) updatedUserCustomCreator.getUser(Role.ORGANIZER);;
        updatedUserRepositoryService.add(updatedOrganizer1);
        List<?extends UpdatedUser> list = updatedUserRepositoryService.findAllByRole(Role.ORGANIZER);
        Assertions.assertEquals(list.get(list.size()-2).getEmail(),updatedOrganizer.getEmail());
        Assertions.assertEquals(list.get(list.size()-1).getEmail(),updatedOrganizer1.getEmail());
    }

}


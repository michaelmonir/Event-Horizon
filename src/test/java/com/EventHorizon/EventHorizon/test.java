package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedOrganizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class test {

    @Autowired
    private  UpdatedUserRepositoryService updatedUserRepositoryService;

    @Test
    void test1() {
        UpdatedOrganizer updatedOrganizer = getOrganizer("faris");
        updatedUserRepositoryService.add(updatedOrganizer);
        UpdatedOrganizer updatedOrganizer1 = (UpdatedOrganizer) updatedUserRepositoryService.getById(updatedOrganizer.getId());
        Assertions.assertEquals(updatedOrganizer1.getFirstName(), updatedOrganizer.getFirstName());
    }
    private UpdatedOrganizer getOrganizer(String z){
        UpdatedOrganizer updatedOrganizer =  UpdatedOrganizer.builder().
                firstName(z).email(z)
                .gender(Gender.MALE).lastName(z)
                .role(Role.CLIENT).password(z)
                .payPalAccount(z).userName(z)
                .rate(5)
                .build();
        return updatedOrganizer;
    }
}


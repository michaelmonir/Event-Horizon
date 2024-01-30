package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedClient;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedOrganizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class test {

    @Autowired
    private  UpdatedUserRepositoryService updatedUserRepositoryService;

    @Test
    void test1() {
        String z = "faris" ;
        UpdatedOrganizer updatedOrganizer =  UpdatedOrganizer.builder().
                firstName(z).email(z)
                .gender(Gender.MALE).lastName(z)
                .role(Role.CLIENT).password(z)
                .payPalAccount(z).userName(z)
                .rate(5)
                .build();
        updatedUserRepositoryService.add(updatedOrganizer);
        UpdatedOrganizer updatedOrganizer1 = (UpdatedOrganizer) updatedUserRepositoryService.getById(updatedOrganizer.getId());
        assertEquals(updatedOrganizer1.getFirstName(),updatedOrganizer.getFirstName());
    }
}

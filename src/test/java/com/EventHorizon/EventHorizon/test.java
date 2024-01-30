package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedClient;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

    @Autowired
    private  UpdatedUserRepositoryService updatedUserRepositoryService;

    @Test
    void test1() {
        String z = "faris" ;
        UpdatedClient updatedClient =  UpdatedClient.builder().
                firstName(z).email(z)
                .gender(Gender.MALE).lastName(z)
                .role(Role.CLIENT).password(z)
                .payPalAccount(z).userName(z)
                .build();
        updatedUserRepositoryService.add(updatedClient);

    }
}

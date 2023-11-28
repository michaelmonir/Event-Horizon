package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.Services.InformationService;
import com.EventHorizon.EventHorizon.Services.ModeratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private InformationService informationService;
    @Autowired
    InformationCreator informationCreator;

    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        informationService.add(information);

        Moderator m1 = moderatorService.getByInformation(information);

        Information i1 = informationService.getByID(m1.getInformation().getId());
        Assertions.assertTrue(information.equals(i1));
    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        informationService.add(information);
        informationService.delete(information.getId());

        Assertions.assertThrows(
                ModeratorNotFoundException.class, () -> {
                    moderatorService.getByID(information.getId());
                }
        );
    }


    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        informationService.add(information);
        Moderator m = moderatorService.getByInformation(information);
        Moderator m1=moderatorService.getByID(m.getId());
        Information i1 = informationService.getByID(m1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        informationService.add(information);

        Moderator m1 = moderatorService.getByInformation(information);

        Assertions.assertEquals(m1.getInformation(), information);
    }
}
package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.OrganizerNotFoundException;
import com.EventHorizon.EventHorizon.Services.InformationService;
import com.EventHorizon.EventHorizon.Services.OrganizerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrganizerRepositoryTest {
    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private InformationService informationService;
    @Autowired
    InformationCreator informationCreator;

    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        informationService.add(information);

        Organizer o1 = organizerService.getByInformation(information);
        Information i1 = informationService.getByID(o1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));
    }


    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        informationService.add(information);
        informationService.delete(information.getId());
        Assertions.assertThrows(
                OrganizerNotFoundException.class, () -> {
                    organizerService.getByInformation(information);
                }
        );
    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        informationService.add(information);

        Organizer o1 = organizerService.getByInformation(information);

        Assertions.assertEquals(o1.getInformation(), information);
    }
}
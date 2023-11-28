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
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);

        Organizer o1 = organizerService.getByID(organizer.getId());
        Information i1 = informationService.getByID(o1.getInformation().getId());


        Assertions.assertTrue(information.equals(i1));

    }


    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);

        organizerService.delete(organizer.getId());

        Assertions.assertThrows(
                OrganizerNotFoundException.class, () -> {
                    organizerService.getByID(organizer.getId());
                }
        );
    }


    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer client = Organizer.builder().information(information).build();
        organizerService.add(client);

        Organizer o1 = organizerService.getByID(client.getId());


        Information i1 = informationService.getByID(o1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));
    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);

        Organizer o1 = organizerService.getByInformation(information);

        Assertions.assertEquals(o1, organizer);
    }

    @Test
    public void updateWithDtoTest() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Information information2 = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);
        information2.setId(information.getId());
        UpdateInformationDTO updateInformationDTO = new UpdateInformationDTO(information2);
        ViewInformationDTO viewInformationDTO = informationService.updateWithDto(updateInformationDTO);
        Information i3 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i3.getFirstName(), information2.getFirstName());
        Assertions.assertEquals(i3.getGender(), information2.getGender());
        Assertions.assertEquals(i3.userName, information.userName);
        Assertions.assertEquals(i3.getId(), information.getId());
    }
}
package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
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
    public void update() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);
        Information information2 = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer2 = Organizer.builder().information(information2).build();
        organizerService.update(organizer.getId(), organizer2);
        Organizer o1 = organizerService.getByID(organizer.getId());
        Information i1 = informationService.getByID(o1.getInformation().getId());


        Assertions.assertTrue(information2.equals(i1));

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

}
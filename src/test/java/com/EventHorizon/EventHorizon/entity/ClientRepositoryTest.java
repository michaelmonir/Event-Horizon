package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Services.ClientService;
import com.EventHorizon.EventHorizon.Services.InformationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientRepositoryTest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private InformationService informationService;
    @Autowired
    InformationCreator informationCreator;
    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByID(client.getId());
        Information i1 = informationService.getByID(c1.getInformation().getId());
        Assertions.assertTrue(information.equals(i1));
    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        Client client = Client.builder().information(information).build();
        clientService.add(client);
        clientService.delete(client.getId());
        Assertions.assertThrows(
                ClientNotFoundException.class, () -> {
                    clientService.getByID(client.getId());
                }
        );
    }

    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByID(client.getId());


        Information i1 = informationService.getByID(c1.getInformation().getId());
        Assertions.assertTrue(information.equals(i1));
    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByInformation(information);

        Assertions.assertEquals(c1, client);
    }
}
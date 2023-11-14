package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.services.ClientService;
import com.EventHorizon.EventHorizon.services.InformationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClientRepositoryTest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private InformationService informationService;
    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByID(client.getId());
        Information i1 = informationService.getByID(c1.getInformation().getId());

        Assertions.assertEquals(information.getEmail(), i1.getEmail());
        Assertions.assertEquals(information.getFirstName(), i1.getFirstName());
        Assertions.assertEquals(information.getLastName(), i1.getLastName());
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        clientService.delete(client.getId());
        Client c1 = clientService.getByID(client.getId());

       Assertions.assertEquals(c1, null);
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);
        Information information2 = Information.builder().
                firstName("faris").email("faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("client").password("pass1234")
                .payPalAccount("faris007@pay2").userName("faris007")
                .build();
        Client client2 = Client.builder().information(information2).build();
        clientService.update(client.getId(), client2);
        Client c1 = clientService.getByID(client.getId());
        Information i1 = informationService.getByID(c1.getInformation().getId());


        Assertions.assertEquals(information2.getEmail(), i1.getEmail());
        Assertions.assertEquals(information2.getFirstName(), i1.getFirstName());
        Assertions.assertEquals("mohamed2", i1.getLastName());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByID(client.getId());


        Information i1 = informationService.getByID(c1.getInformation().getId());
        Assertions.assertEquals("fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
    }
}
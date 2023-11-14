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
                firstName("fares").email("1fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("1fares007@pay2").userName("1fares007")
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
                firstName("fares").email("2fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("2fares007@pay2").userName("2fares007")
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
                firstName("fares").email("3fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("3fares007@pay2").userName("3fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);
        Information information2 = Information.builder().
                firstName("faris").email("4faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("client").password("pass1234")
                .payPalAccount("4faris007@pay2").userName("4faris007")
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
                firstName("fares").email("5fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("5fares007@pay2").userName("5fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByID(client.getId());


        Information i1 = informationService.getByID(c1.getInformation().getId());
        Assertions.assertEquals("5fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
    }

    @Test
    public void getByInformation() {
        Information information = Information.builder().
                firstName("fares").email("6fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("6fares007@pay2").userName("6fares007")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.add(client);

        Client c1 = clientService.getByInformation(information);

        Assertions.assertEquals(c1, client);
    }
}
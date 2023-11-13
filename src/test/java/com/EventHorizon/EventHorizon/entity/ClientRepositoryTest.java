package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("new2")
                .email("new2")
                .gender("new2")
                .lastName("new2")
                .role("new2")
                .password("new2")
                .payPalAccount("new@pay2")
                .userName("new2")
                .build();
        Client client = Client.builder()
                .information(information)
                .build();
        clientService.add(client);
    }

    @Test
    public void delete() {
        clientService.delete(1);
    }


    @Test
    public void update() {
        Information information = Information.builder().
                firstName("done")
                .email("done")
                .gender("done")
                .lastName("done")
                .role("done")
                .password("done")
                .payPalAccount("@done")
                .userName("done")
                .build();
        Client client = Client.builder().information(information).build();
        clientService.update(2, client);
    }

    @Test
    public void getByID() {
        Client client = clientService.getByID(2);
        System.out.println("client = " + client);
    }
}
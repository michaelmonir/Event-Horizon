package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("ahmed")
                .email("ahmed@gmail.com")
                .gender("male")
                .lastName("ahmed")
                .role("organizer")
                .password("pass4321")
                .payPalAccount("ahmed007@pay")
                .userName("ahmed_hossam")
                .build();
        Client client = Client.builder()
                .information(information)
                .build();
        System.out.println(client);
        clientRepository.save(client);
    }

}
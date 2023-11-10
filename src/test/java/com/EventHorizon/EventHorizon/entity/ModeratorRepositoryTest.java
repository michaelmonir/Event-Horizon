package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorRepository moderatorRepository;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("lasasdsadtone")
                .email("ahmesadd@gmail.com")
                .gender("masdale")
                .lastName("lastone")
                .role("Moderator")
                .password("asdapass4321")
                .payPalAccount("ahmed007@pay")
                .userName("ahmed_hadasossam")
                .build();
        Moderator moderator = Moderator.builder()
                .information(information)
                .build();

        moderatorRepository.save(moderator);
    }
}
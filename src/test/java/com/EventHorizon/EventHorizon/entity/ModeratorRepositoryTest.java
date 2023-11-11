package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.services.ClientService;
import com.EventHorizon.EventHorizon.services.ModeratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorService moderatorService;

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
        Moderator moderator = Moderator.builder()
                .information(information)
                .build();
        moderatorService.add(moderator);
    }

    @Test
    public void delete() {
        moderatorService.delete(1);
    }


    @Test
    public void update() {
        Information information = Information.builder().
                firstName("done2")
                .email("don2e")
                .gender("done2")
                .lastName("don2e")
                .role("do2ne")
                .password("do2ne")
                .payPalAccount("@d2one")
                .userName("d2one")
                .build();
        Moderator moderator = Moderator.builder()
                .information(information)
                .build();
        moderatorService.update(2, moderator);
    }

    @Test
    public void getByID() {
        Moderator moderator = moderatorService.getByID(2);
        System.out.println("client = " + moderator);
    }
}
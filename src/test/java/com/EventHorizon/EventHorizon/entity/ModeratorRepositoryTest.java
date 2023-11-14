package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.services.ClientService;
import com.EventHorizon.EventHorizon.services.InformationService;
import com.EventHorizon.EventHorizon.services.ModeratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private InformationService informationService;

    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("18fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("moderator").password("pass1234")
                .payPalAccount("18fares007@pay2").userName("18fares007")
                .build();
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        Moderator m1 = moderatorService.getByID(moderator.getId());
        Information i1 = informationService.getByID(m1.getInformation().getId());

        Assertions.assertEquals(information.getEmail(), i1.getEmail());
        Assertions.assertEquals(information.getFirstName(), i1.getFirstName());
        Assertions.assertEquals(information.getLastName(), i1.getLastName());
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("19fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("moderator").password("pass1234")
                .payPalAccount("19fares007@pay2").userName("19fares007")
                .build();
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        moderatorService.delete(moderator.getId());
        Moderator m1 = moderatorService.getByID(moderator.getId());

        Assertions.assertEquals(m1, null);
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("20fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("moderator").password("pass1234")
                .payPalAccount("20fares007@pay2").userName("20fares007")
                .build();
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);
        Information information2 = Information.builder().
                firstName("faris").email("21faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("moderator").password("pass1234")
                .payPalAccount("21faris007@pay2").userName("21faris007")
                .build();
        Moderator moderator2 = Moderator.builder().information(information2).build();
        moderatorService.update(moderator.getId(), moderator2);
        Moderator m1 = moderatorService.getByID(moderator.getId());
        Information i1 = informationService.getByID(m1.getInformation().getId());


        Assertions.assertEquals(information2.getEmail(), i1.getEmail());
        Assertions.assertEquals(information2.getFirstName(), i1.getFirstName());
        Assertions.assertEquals("mohamed2", i1.getLastName());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("22fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("moderator").password("pass1234")
                .payPalAccount("22fares007@pay2").userName("22fares007")
                .build();
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);
        Moderator m1 = moderatorService.getByID(moderator.getId());

        Information i1 = informationService.getByID(m1.getInformation().getId());
        Assertions.assertEquals("22fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
    }

    @Test
    public void getByInformation() {
        Information information = Information.builder().
                firstName("fares").email("23fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("23fares007@pay2").userName("23fares007")
                .build();
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        Moderator m1 = moderatorService.getByInformation(information);

        Assertions.assertEquals(m1, moderator);
    }
}
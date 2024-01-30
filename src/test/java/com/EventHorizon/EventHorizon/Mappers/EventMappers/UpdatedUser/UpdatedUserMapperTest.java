package com.EventHorizon.EventHorizon.Mappers.EventMappers.UpdatedUser;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UpdatedUserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdatedUserMapperTest {
    @Autowired
    private UpdatedUserMapper updatedUserMapper;


    @Test
    void clientCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.CLIENT);
        UpdatedClient updatedClient = (UpdatedClient) updatedUserMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedClient.getClass(), UpdatedClient.class);
    }

    @Test
    void organizerCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.ORGANIZER);
        UpdatedOrganizer updatedOrganizer = (UpdatedOrganizer) updatedUserMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedOrganizer.getClass(), UpdatedOrganizer.class);
    }

    @Test
    void sponsorCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.SPONSOR);
        UpdatedSponsor updatedUser = (UpdatedSponsor) updatedUserMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), UpdatedSponsor.class);
    }

    @Test
    void adminCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.ADMIN);
        UpdatedAdmin updatedUser = (UpdatedAdmin) updatedUserMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), UpdatedAdmin.class);
    }

    @Test
    void moderatorCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.MODERATOR);
        UpdatedModerator updatedUser = (UpdatedModerator) updatedUserMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), UpdatedModerator.class);
    }

    private UpdatedUserDto createUpdatedUserDto(Role role) {
        String name = "faris";
        return UpdatedUserDto.builder().
                firstName(name).email(name)
                .lastName(name)
                .role(role.toString()).password(name)
                .payPalAccount(name).userName(name)
                .build();
    }
}

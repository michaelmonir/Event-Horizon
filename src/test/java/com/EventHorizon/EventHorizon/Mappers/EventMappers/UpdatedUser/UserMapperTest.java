package com.EventHorizon.EventHorizon.Mappers.EventMappers.UpdatedUser;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    void clientCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.CLIENT);
        Client client = (Client) userMapper.createUser(updatedUserDto);
        Assertions.assertEquals(client.getClass(), Client.class);
    }

    @Test
    void organizerCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.ORGANIZER);
        Organizer organizer = (Organizer) userMapper.createUser(updatedUserDto);
        Assertions.assertEquals(organizer.getClass(), Organizer.class);
    }

    @Test
    void sponsorCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.SPONSOR);
        Sponsor updatedUser = (Sponsor) userMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), Sponsor.class);
    }

    @Test
    void adminCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.ADMIN);
        Admin updatedUser = (Admin) userMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), Admin.class);
    }

    @Test
    void moderatorCreationTest() {
        UpdatedUserDto updatedUserDto = createUpdatedUserDto(Role.MODERATOR);
        Moderator updatedUser = (Moderator) userMapper.createUser(updatedUserDto);
        Assertions.assertEquals(updatedUser.getClass(), Moderator.class);
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

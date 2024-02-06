package com.EventHorizon.EventHorizon.Mappers.EventMappers.User;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdationDTO;
import com.EventHorizon.EventHorizon.Entities.User.*;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Mappers.User.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    void clientCreationTest() {
        UserCreationDto userCreationDto = createUserCreationDto(Role.CLIENT);
        Client client = (Client) userMapper.createUser(userCreationDto);
        Assertions.assertEquals(client.getClass(), Client.class);
    }

    @Test
    void organizerCreationTest() {
        UserCreationDto userCreationDto = createUserCreationDto(Role.ORGANIZER);
        Organizer organizer = (Organizer) userMapper.createUser(userCreationDto);
        Assertions.assertEquals(organizer.getClass(), Organizer.class);
    }

    @Test
    void sponsorCreationTest() {
        UserCreationDto userCreationDto = createUserCreationDto(Role.SPONSOR);
        Sponsor updatedUser = (Sponsor) userMapper.createUser(userCreationDto);
        Assertions.assertEquals(updatedUser.getClass(), Sponsor.class);
    }

    @Test
    void adminCreationTest() {
        UserCreationDto userCreationDto = createUserCreationDto(Role.ADMIN);
        Admin updatedUser = (Admin) userMapper.createUser(userCreationDto);
        Assertions.assertEquals(updatedUser.getClass(), Admin.class);
    }

    @Test
    void moderatorCreationTest() {
        UserCreationDto userCreationDto = createUserCreationDto(Role.MODERATOR);
        Moderator updatedUser = (Moderator) userMapper.createUser(userCreationDto);
        Assertions.assertEquals(updatedUser.getClass(), Moderator.class);
    }

    @Test
    void updateUserTest() {
        Client client = (Client)userCustomCreator.getUser(Role.CLIENT);
        client.setGender(Gender.MALE);
        UserUpdationDTO updatedUserDto = this.createUserUpdationDto();

        userMapper.updateUser(client, updatedUserDto);
        Assertions.assertEquals(client.getFirstName(), "new value");
        Assertions.assertEquals(client.getLastName(), "new value");
        Assertions.assertEquals(client.getPayPalAccount(), "new value");
        Assertions.assertEquals(client.getGender(), Gender.FEMALE);
    }

    private UserCreationDto createUserCreationDto(Role role) {
        String name = "faris";
        return UserCreationDto.builder().
                firstName(name).email(name)
                .lastName(name)
                .role(role.toString()).password(name)
                .payPalAccount(name).userName(name)
                .build();
    }

    private UserUpdationDTO createUserUpdationDto() {
        String name = "new value";
        return UserUpdationDTO.builder().
                firstName(name)
                .lastName(name)
                .payPalAccount(name)
                .gender(Gender.FEMALE.toString())
                .build();
    }
}

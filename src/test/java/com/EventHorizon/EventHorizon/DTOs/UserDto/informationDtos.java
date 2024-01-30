package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class informationDtos {

    @Autowired
    UserCustomCreator userCustomCreator;


    @Test
    void ToInformationDto() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        UpdatedUserDto updatedUserDto = new UpdatedUserDto(client);
        Assert.assertEquals(client.userName, updatedUserDto.getUserName());
        Assert.assertEquals(client.getFirstName(), updatedUserDto.getFirstName());
        Assert.assertEquals(client.getLastName(), updatedUserDto.getLastName());
        Assert.assertEquals(client.getEmail(), updatedUserDto.getEmail());
        Assert.assertEquals(client.getGender().toString(), updatedUserDto.getGender());
        Assert.assertEquals(client.getRole().toString(), updatedUserDto.getRole());
        Assert.assertEquals(client.getSignInWithEmail(), updatedUserDto.getSignInWithEmail());
    }

    @Test
    void ToUpdatedDto() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        UserUpdateDTO informationDTO=new UserUpdateDTO(client);
        Assert.assertEquals(client.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(client.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(client.getGender().toString(), informationDTO.getGender());
    }


    @Test
    void ToViewDto() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        UserViewDTO informationDTO=new UserViewDTO(user);

        Assert.assertEquals(user.userName, informationDTO.getUserName());
        Assert.assertEquals(user.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(user.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(user.getEmail(), informationDTO.getEmail());
        Assert.assertEquals(user.getGender().toString(), informationDTO.getGender());
        Assert.assertEquals(user.getRole().toString(), informationDTO.getRole());
    }

    @Test
    void ToInformationTest() {
        User user = userCustomCreator.getUser(Role.CLIENT);
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(user);
        User information1 = userUpdateDTO.toInformation(user);
        Assert.assertEquals(information1.getRole(), user.getRole());
    }
}

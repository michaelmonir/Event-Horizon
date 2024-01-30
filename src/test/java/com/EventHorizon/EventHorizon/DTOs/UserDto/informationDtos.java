package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
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
        Information information = userCustomCreator.getInformation(Role.CLIENT);
        UserViewDTO informationDTO=new UserViewDTO(information);

        Assert.assertEquals(information.userName, informationDTO.getUserName());
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getEmail(), informationDTO.getEmail());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
        Assert.assertEquals(information.getRole().toString(), informationDTO.getRole());
    }

    @Test
    void ToInformationTest() {
        Information information = userCustomCreator.getInformation(Role.CLIENT);
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(information);
        Information information1 = userUpdateDTO.toInformation(information);
        Assert.assertEquals(information1.getRole(), information.getRole());

    }

}

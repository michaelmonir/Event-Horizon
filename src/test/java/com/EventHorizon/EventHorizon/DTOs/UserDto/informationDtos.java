package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class informationDtos {

    @Autowired
    UserCustomCreator userCustomCreator;

    @Test
    void ToUpdatedDto() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        UserUpdationDTO informationDTO=new UserUpdationDTO(client);
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
        UserUpdationDTO userUpdationDTO = new UserUpdationDTO(user);
        User information1 = userUpdationDTO.toInformation(user);
        Assert.assertEquals(information1.getRole(), user.getRole());
    }
}

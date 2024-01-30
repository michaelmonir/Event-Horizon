package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class informationDtos {

    @Autowired
    InformationCustomCreator informationCustomCreator;


    @Test
    void ToInformationDto() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        UpdatedUserDto updatedUserDto = new UpdatedUserDto(information);
        Assert.assertEquals(information.userName, updatedUserDto.getUserName());
        Assert.assertEquals(information.getFirstName(), updatedUserDto.getFirstName());
        Assert.assertEquals(information.getLastName(), updatedUserDto.getLastName());
        Assert.assertEquals(information.getEmail(), updatedUserDto.getEmail());
        Assert.assertEquals(information.getGender().toString(), updatedUserDto.getGender());
        Assert.assertEquals(information.getRole().toString(), updatedUserDto.getRole());
        Assert.assertEquals(information.getSignInWithEmail(), updatedUserDto.getSignInWithEmail());
    }

    @Test
    void ToUpdatedDto() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        UpdateInformationDTO informationDTO=new UpdateInformationDTO(information);
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
    }


    @Test
    void ToViewDto() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        ViewInformationDTO informationDTO=new ViewInformationDTO(information);

        Assert.assertEquals(information.userName, informationDTO.getUserName());
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getEmail(), informationDTO.getEmail());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
        Assert.assertEquals(information.getRole().toString(), informationDTO.getRole());
    }

    @Test
    void ToInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        UpdateInformationDTO updateInformationDTO = new UpdateInformationDTO(information);
        Information information1 = updateInformationDTO.toInformation(information);
        Assert.assertEquals(information1.getRole(), information.getRole());

    }

}

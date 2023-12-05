package com.EventHorizon.EventHorizon.UserDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.InformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class informationDtos {

    @Autowired
    InformationCreator informationCreator;


    @Test
    void ToInformationDto() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        InformationDTO informationDTO = new InformationDTO(information);
        Assert.assertEquals(information.userName, informationDTO.getUserName());
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getEmail(), informationDTO.getEmail());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
        Assert.assertEquals(information.getRole().toString(), informationDTO.getRole());
        Assert.assertEquals(information.getSignInWithEmail(), informationDTO.getSignInWithEmail());
    }

    @Test
    void ToUpdatedDto() {
        Information information = informationCreator.getInformation(Role.CLIENT);
        UpdateInformationDTO informationDTO=new UpdateInformationDTO(information);
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
    }


    @Test
    void ToViewDto() {
        Information information = informationCreator.getInformation(Role.CLIENT);
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
        Information information = informationCreator.getInformation(Role.CLIENT);
        UpdateInformationDTO updateInformationDTO = new UpdateInformationDTO(information);
        Information information1 = updateInformationDTO.toInformation(information);
        Assert.assertEquals(information1.getRole(), information.getRole());

    }

}

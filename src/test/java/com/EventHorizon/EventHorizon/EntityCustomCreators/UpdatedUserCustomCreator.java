package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedClient;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UpdatedUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatedUserCustomCreator {

    @Autowired
    private UpdatedUserMapper updatedUserMapper;
    static int valueOfTest = 0;

    private UpdatedUser getUser(Role role) {
        String z = "faris" + (valueOfTest);
        UpdatedUserDto updatedUserDto =UpdatedUserDto.builder()
                .firstName(z).email(z)
                .lastName(z)
                .role(role.toString()).password(z)
                .payPalAccount(z).userName(z)
                .build();
        valueOfTest++;
        return updatedUserMapper.createUser(updatedUserDto);
    }
}

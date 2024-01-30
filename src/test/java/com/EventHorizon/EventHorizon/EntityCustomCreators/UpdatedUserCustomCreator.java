package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatedUserCustomCreator {

    @Autowired
    private UserMapper userMapper;
    static int valueOfTest = 0;

    public User getUser(Role role) {
        String z = "faris" + (valueOfTest);
        UpdatedUserDto updatedUserDto =UpdatedUserDto.builder()
                .firstName(z).email(z)
                .lastName(z)
                .role(role.toString()).password(z)
                .payPalAccount(z).userName(z)
                .build();
        valueOfTest++;
        return userMapper.createUser(updatedUserDto);
    }
}

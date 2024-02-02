package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomCreator {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepositoryService userRepositoryService;

    static int valueOfTest = 0;

    public User getUser(Role role) {
        String z = "faris" + (valueOfTest);
        UpdatedUserDto updatedUserDto =UpdatedUserDto.builder()
                .firstName(z).email(z+"@gmail.com")
                .lastName(z)
                .role(role.toString()).password(z)
                .payPalAccount(z).userName(z+"userName")
                .build();
        valueOfTest++;
        return userMapper.createUser(updatedUserDto);
    }

    public User getAndSaveUser(Role role) {
        User user = this.getUser(role);
        this.userRepositoryService.create(user);
        return user;
    }
}

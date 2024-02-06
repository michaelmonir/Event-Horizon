package com.EventHorizon.EventHorizon.EntityCustomCreators.User;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Mappers.User.UserMapper;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomCreator {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    static int valueOfTest = 0;

    public User getUser(Role role) {
        String z = "faris" + (valueOfTest);
        UserCreationDto userCreationDto = UserCreationDto.builder()
                .firstName(z).email(z+"@gmail.com")
                .lastName(z)
                .role(role.toString()).password(z)
                .payPalAccount(z).userName(z+"userName")
                .build();
        valueOfTest++;
        return userMapper.createUser(userCreationDto);
    }

    public User getAndSaveUser(Role role) {
        User user = this.getUser(role);
        this.userRepository.save(user);
        return user;
    }
}

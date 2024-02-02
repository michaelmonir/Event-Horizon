package com.EventHorizon.EventHorizon.EntityCustomCreators.Dtos;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import org.springframework.stereotype.Service;

@Service
public class UserCreationDtoCustomCreator {

    public UserCreationDto getDtoFromUser(User user) {
        return UserCreationDto.builder()
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .gender(user.getGender().toString())
                .payPalAccount(user.getPayPalAccount())
                .role(user.getRole().toString())
                .signInWithEmail(user.getSignInWithEmail())
                .build();
    }
}

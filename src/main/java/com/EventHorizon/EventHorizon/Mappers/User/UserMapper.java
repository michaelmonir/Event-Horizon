package com.EventHorizon.EventHorizon.Mappers.User;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdationDTO;
import com.EventHorizon.EventHorizon.Entities.User.*;
import com.EventHorizon.EventHorizon.Entities.User.User.UserBuilder;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.User.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createUser(UserCreationDto registerRequest) {
        return getUserBuilderByRole(registerRequest)
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.fromString(registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(Gender.MALE)
                .payPalAccount(registerRequest.getPayPalAccount())
                .active(0)
                .signInWithEmail(registerRequest.getSignInWithEmail())
                .build();
    }

    public void updateUser(User user, UserUpdationDTO userUpdationDTO) {
        user.setFirstName(userUpdationDTO.getFirstName());
        user.setLastName(userUpdationDTO.getLastName());
        user.setGender(Gender.valueOf(userUpdationDTO.getGender()));
        user.setPayPalAccount(userUpdationDTO.getPayPalAccount());
    }

    private UserBuilder getUserBuilderByRole(UserCreationDto registerRequest) {
        return switch (registerRequest.getRole()) {
            case "ROLE_ADMIN" -> Admin.builder();
            case "ROLE_ORGANIZER" -> Organizer.builder();
            case "ROLE_CLIENT" -> Client.builder();
            case "ROLE_SPONSOR" -> Sponsor.builder();
            case "ROLE_MODERATOR" -> Moderator.builder();
            default -> throw new RoleNotFoundException();
        };
    }
}

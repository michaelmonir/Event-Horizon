package com.EventHorizon.EventHorizon.Mappers.UpdatedUser;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser.UpdatedUserBuilder;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatedUserMapper {

    private final PasswordEncoder passwordEncoder;

    public UpdatedUser createUser(UpdatedUserDto registerRequest) {
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

    private UpdatedUserBuilder getUserBuilderByRole(UpdatedUserDto registerRequest) {
        return switch (registerRequest.getRole()) {
            case "ROLE_ADMIN" -> UpdatedAdmin.builder();
            case "ROLE_ORGANIZER" -> UpdatedOrganizer.builder();
            case "ROLE_CLIENT" -> UpdatedClient.builder();
            case "ROLE_SPONSOR" -> UpdatedSponsor.builder();
            case "ROLE_MODERATOR" -> UpdatedModerator.builder();
            default -> throw new RoleNotFoundException();
        };
    }
}

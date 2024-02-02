package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserCreationDto {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String gender;
    private String payPalAccount;
    private String role;
    private int signInWithEmail;
}

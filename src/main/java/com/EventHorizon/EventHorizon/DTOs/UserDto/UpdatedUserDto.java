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

public class UpdatedUserDto {
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

    public UpdatedUserDto(User information) {
        this.id = information.getId();
        this.email = information.getEmail();
        this.firstName = information.getFirstName();
        this.password = information.getPassword();
        this.userName = information.userName;
        this.lastName = information.getLastName();
        this.gender = information.getGender().toString();
        this.role = information.getRole().toString();
        this.signInWithEmail = information.getSignInWithEmail();
        this.payPalAccount = information.getPayPalAccount();
    }
}

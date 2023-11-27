package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InformationDTO {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String gender;
    private String payPalAccount;
    private String role;
    int signInWithEmail;

    InformationDTO(Information information) {
        this.id = information.getId();
        this.email = information.getEmail();
        this.firstName = information.getFirstName();
        this.userName = information.userName;
        this.lastName = information.getLastName();
        this.gender = information.getGender();
        this.role = information.getRole();
        this.signInWithEmail = information.getSignInWithEmail();
        this.payPalAccount = information.getPayPalAccount();
    }
}

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
public class UserViewDTO {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String payPalAccount;
    private String role;
    private UserCreationDTO userCreationDTO;

    public UserViewDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.userName = user.userName;
        this.lastName = user.getLastName();
        this.gender = user.getGender().toString();
        this.role = user.getRole().toString();
        this.payPalAccount = user.getPayPalAccount();
    }
}

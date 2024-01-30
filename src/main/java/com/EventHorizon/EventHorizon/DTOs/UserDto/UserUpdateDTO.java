package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserUpdateDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String payPalAccount;

    private UserCreationDTO userCreationDTO;

    public UserUpdateDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender().toString();
        this.payPalAccount = user.getPayPalAccount();

    }
    public User toInformation(User user){
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setGender(Gender.fromString(this.gender));
        user.setPayPalAccount(this.payPalAccount);
        return user;
    }
}

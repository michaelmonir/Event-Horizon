package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateInformationDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String payPalAccount;

    private UserDTO userDTO;

    public UpdateInformationDTO(Information information) {
        this.id = information.getId();
        this.firstName = information.getFirstName();
        this.lastName = information.getLastName();
        this.gender = information.getGender().toString();
        this.payPalAccount = information.getPayPalAccount();

    }
    public Information toInformation(Information information){
        information.setFirstName(this.firstName);
        information.setLastName(this.lastName);
        information.setGender(Gender.fromString(this.gender));
        information.setPayPalAccount(this.payPalAccount);
        return information;
    }
}

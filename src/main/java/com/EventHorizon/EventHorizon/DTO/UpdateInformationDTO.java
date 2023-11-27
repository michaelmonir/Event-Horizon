package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Information;
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

    UpdateInformationDTO(Information information) {
        this.id = information.getId();
        this.firstName = information.getFirstName();
        this.lastName = information.getLastName();
        this.gender = information.getGender();
        this.payPalAccount = information.getPayPalAccount();
    }

}

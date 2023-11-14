package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public static InformationDTO toDTO(Information information) {
        return InformationDTO.builder()
                .id(information.getId())
                .email(information.getEmail())
                .firstName(information.getFirstName())
                .lastName(information.getLastName())
                .gender(information.getGender())
                .userName(information.userName)
                .role(information.getRole())
                .signInWithEmail(information.getSignInWithEmail())
                .payPalAccount(information.getPayPalAccount())
                .build();
    }
}

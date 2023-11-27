package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.services.InformationService;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

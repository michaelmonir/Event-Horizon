package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class InformationCreator {
    int valueOfTest = 0;

    public Information getInformation(Role role) {
        String z = "faris" + (valueOfTest);
        Information information = Information.builder().
                firstName(z).email(z)
                .gender(Gender.MALE).lastName(z)
                .role(role).password(z)
                .payPalAccount(z).userName(z)
                .build();
        valueOfTest++;
        return information;
    }
}

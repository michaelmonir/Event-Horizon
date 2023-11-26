package com.EventHorizon.EventHorizon.entity;

import org.springframework.stereotype.Service;

@Service
public class InformationCreator {
    int valueOfTest = 0;

    public Information getInformation(String rol) {
        String z = "faris" + (valueOfTest);
        Information information = Information.builder().
                firstName(z).email(z)
                .gender("male").lastName(z)
                .role(rol).password(z)
                .payPalAccount(z).userName(z)
                .build();
        valueOfTest++;
        return information;
    }
}

package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.entity.Information;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier
public interface UserInformationService {

    void add(Information information);

    void delete(Information information);
}
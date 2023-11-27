package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTO.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTO.ViewInformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier
public interface UserInformationService {

    void add(Information information);

    void delete(Information information);
    Information update(UpdateInformationDTO updateInformationDTO, Information information);

}

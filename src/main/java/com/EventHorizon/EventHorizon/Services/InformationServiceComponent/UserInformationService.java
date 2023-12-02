package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;

public interface UserInformationService {

    void add(Information information);

    void delete(Information information);
    Information update(UpdateInformationDTO updateInformationDTO, Information information);

    User getUserByInformation(Information information);
}

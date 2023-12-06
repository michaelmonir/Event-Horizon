package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;

public interface UserInformationRepositoryService extends SuperUserInformationRepositoryService {

    void add(Information information);

    void delete(Information information);

    User getUserByInformation(Information information);

}

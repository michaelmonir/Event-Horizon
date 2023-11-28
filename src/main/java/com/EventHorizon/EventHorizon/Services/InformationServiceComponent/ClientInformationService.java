package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Repository.ClientRepository;
import com.EventHorizon.EventHorizon.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ClientInformationService implements UserInformationService {

    @Autowired
    private ClientService clientService;

    @Override
    public void add(Information information) {
        Client client = Client.builder().information(information).build();
        clientService.add(client);
    }

    @Override
    public void delete(Information information) {
        Client c1 = clientService.getByInformation(information);
        clientService.delete(c1.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Client client = clientService.getByInformation(information);
        client.setInformation(updateInformationDTO.toInformation(information));
        clientService.add(client);
        return (client.getInformation());
    }
}

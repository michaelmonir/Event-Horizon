package com.EventHorizon.EventHorizon.services.InformationServiceMichael;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.services.InformationServiceModified.InformationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientInformationService implements UserInformationService
{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void add(Information information, boolean addUser)
    {
        Client client = Client.builder().information(information).build();
        clientRepository.save(client);
    }

    @Override
    public void delete(int id, Information information)
    {
        Client c1 = clientRepository.findByInformation(information);
        clientRepository.delete(c1);
    }
}

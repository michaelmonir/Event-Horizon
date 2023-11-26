package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ClientInformationService implements UserInformationService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void add(Information information) {
        Client client = Client.builder().information(information).build();
        clientRepository.save(client);
    }

    @Override
    public void delete( Information information) {
        Client c1 = clientRepository.findByInformation(information);
        clientRepository.delete(c1);
    }
}

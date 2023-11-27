package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;




@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    InformationService informationService;

    public void add(Client client) {
        clientRepository.save(client);
    }

    public void delete(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void update(int id, Client newOne) {
        Optional<Client> old = clientRepository.findById(id);
        if (old.isPresent()) {
            Client oldOne = old.get();
            informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
            newOne.setId(oldOne.getId());
            clientRepository.save(newOne);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client getByID(int id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client getByInformation(Information information) {
        Optional<Client> client = Optional.of(clientRepository.findByInformation(information));
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException();
        }
    }
}

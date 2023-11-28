package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;




@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

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

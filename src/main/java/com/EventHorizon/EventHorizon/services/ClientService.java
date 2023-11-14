package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
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
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Optional<Client> client = clientRepository.findById(id);
            if (client.isPresent()) {
                clientRepository.deleteById(id);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, Client newOne) {
        try {
            Optional<Client> old = clientRepository.findById(id);
            if (old.isPresent()) {
                Client oldOne = old.get();
                informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
                newOne.setId(oldOne.getId());
                clientRepository.save(newOne);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Client getByID(int id) {
        try {
            Optional<Client> client = clientRepository.findById(id);
            if (client.isPresent()) {
                return client.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

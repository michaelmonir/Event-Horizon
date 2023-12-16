package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ClientNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UserRepositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClientInformationRepositoryService implements UserInformationRepositoryService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public User add(Information information) {
        Client client = Client.builder().information(information).build();
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Information information) {
        Client client = (Client)this.getUserByInformation(information);

        clientRepository.deleteById(client.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Client client = (Client)this.getUserByInformation(information);

        Information newInformation = updateInformationDTO.toInformation(information);
        client.setInformation(newInformation);
        clientRepository.save(client);

        return newInformation;
    }

    public User getUserByInformation(Information information){
        Optional<Client> client = Optional.ofNullable(clientRepository.findByInformation(information));
        if (!client.isPresent())
            throw new ClientNotFoundException();
        return client.get();
    }

    @Override
    public List<? extends User> findAllOfUsers() {
        return clientRepository.findAll();
    }



}

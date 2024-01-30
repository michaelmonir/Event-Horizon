package com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UpdatedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatedUserRepositoryService {

    private final UpdatedUserRepository updatedUserRepository;

    public void add(UpdatedUser updatedUser) {
        if (updatedUser.getId() != 0) {
            throw new AlreadyFoundException();
        }
        this.save(updatedUser);
    }

    private void save(UpdatedUser updatedUser) {
        try {
            updatedUserRepository.save(updatedUser);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyFoundException();// to change
        }
    }

    public void delete(UpdatedUser updatedUser) {
        updatedUserRepository.delete(updatedUser);
    }

    public void deleteById(int id) {
        updatedUserRepository.deleteById(id);
    }

    public void update(UpdatedUser updatedUser) {
        if (updatedUser.getId() == 0) {
            throw new UserNotFoundException();
        }
        try {
            updatedUserRepository.save(updatedUser);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public UpdatedUser getById(int id) {
        Optional<UpdatedUser> updatedUser = updatedUserRepository.findById(id);
        if (updatedUser.isEmpty())
            throw new UserNotFoundException();
        return updatedUser.get();
    }
}
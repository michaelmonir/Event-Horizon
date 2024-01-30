package com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UpdatedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
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
        try {
            updatedUserRepository.delete(updatedUser);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public void deleteById(int id) {
        try {
            updatedUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
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

    public Role getRole(int id) {
        Optional<Role> role = updatedUserRepository.findRoleById(id);
        if (role.isEmpty())
            throw new UserNotFoundException();
        return role.get();
    }

    public List<? extends UpdatedUser> findAllByRole(Role role) {
        return updatedUserRepository.findAllByRole(role);
    }
}
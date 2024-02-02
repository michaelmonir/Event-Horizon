package com.EventHorizon.EventHorizon.RepositoryServices.User;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdateDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserViewDTO;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.User.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.User.InvalidUserDataException;
import com.EventHorizon.EventHorizon.Exceptions.User.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepositoryService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;

    public void create(User user) {
        if (user.getId() != 0)
            throw new AlreadyFoundException();
        this.checkNotAdminOperation(user.getRole());
        this.save(user);
    }

    public void update(User user) {
        this.checkUserExists(user.getId());

        this.checkNotAdminOperation(user.getRole());
        this.save(user);
    }

    public void deleteById(int id) {
        Role role = this.getRoleAndCheckExists(id);
        this.checkNotAdminOperation(role);
        userRepository.deleteById(id);
    }

    public List<? extends User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public Role getRoleAndCheckExists(int id) {
        Optional<Role> role = userRepository.findRoleById(id);
        if (role.isEmpty())
            throw new UserNotFoundException();
        return role.get();
    }

    public void checkUserExists(int id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException();
    }

    private void checkNotAdminOperation(Role role) {
        if (role == Role.ADMIN)
            throw new NotAdminOperationException();
    }

    private void save(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }
}
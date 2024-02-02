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

    private void save(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidUserDataException();
        }
    }

    public void delete(User user) {
        if (user.getRole() == Role.ADMIN)
            throw new NotAdminOperationException();
        getUserRepositoryService.getById(user.getId());
        userRepository.delete(user);
    }

    public void deleteById(int id) {
        User user = getUserRepositoryService.getById(id);
        this.checkNotAdminOperation(user.getRole());
        userRepository.deleteById(id);
    }

    public void update(User user) {
        if (user.getId() == 0)
            throw new UserNotFoundException();
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public Role getRole(int id) {
        Optional<Role> role = userRepository.findRoleById(id);
        if (role.isEmpty())
            throw new UserNotFoundException();
        return role.get();
    }

    public List<? extends User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public UserViewDTO updateWithDto(UserUpdateDTO userUpdateDTO) {
        User user = getUserRepositoryService.getById(userUpdateDTO.getId());
        this.update(user);
        return new UserViewDTO(user);
    }

    private void checkNotAdminOperation(Role role) {
        if (role == Role.ADMIN)
            throw new NotAdminOperationException();
    }
}
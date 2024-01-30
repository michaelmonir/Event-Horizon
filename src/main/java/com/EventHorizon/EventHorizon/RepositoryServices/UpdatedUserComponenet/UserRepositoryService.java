package com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdateDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserViewDTO;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;

    public void add(User user) {
        if (user.getId() != 0)
            throw new AlreadyFoundException();
        if (user.getRole() == Role.ADMIN)
            throw new NotAdminOperationException();
        this.save(user);
    }

    private void save(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyFoundException();// to change
        }
    }

    public void delete(User user) {
        if (user.getRole() == Role.ADMIN)
            throw new NotAdminOperationException();
        this.getById(user.getId());
        userRepository.delete(user);
    }

    public void deleteById(int id) {
        User user = this.getById(id);
        if (user.getRole() == Role.ADMIN)
            throw new NotAdminOperationException();
        userRepository.deleteById(id);
    }

    public void update(User user) {
        if (user.getId() == 0) {
            throw new UserNotFoundException();
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public User getById(int id) {
        Optional<User> updatedUser = userRepository.findById(id);
        if (updatedUser.isEmpty())
            throw new UserNotFoundException();
        return updatedUser.get();
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

    public Client getClientById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.CLIENT)
            throw new UserNotFoundException();
        return (Client) user;
    }

    public Organizer getOrganizerById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.ORGANIZER)
            throw new UserNotFoundException();
        return (Organizer) user;
    }

    public Admin getAdminById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.ADMIN)
            throw new UserNotFoundException();
        return (Admin) user;
    }

    public Sponsor getSponsorById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.SPONSOR)
            throw new UserNotFoundException();
        return (Sponsor) user;
    }
    public Moderator getModeratorById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.MODERATOR)
            throw new UserNotFoundException();
        return (Moderator) user;
    }
    public UserViewDTO updateWithDto(UserUpdateDTO userUpdateDTO) {
        User user = this.getById(userUpdateDTO.getId());
        this.update(user);
        return new UserViewDTO(user);
    }
    public User getByEmail(String email) {
        Optional<User> updatedUser = userRepository.findByEmail(email);
        if (updatedUser.isEmpty())
            throw new UserNotFoundException();
        return updatedUser.get();
    }
    public User getByUserName(String userName) {
        Optional<User> updatedUser = userRepository.findByUserName(userName);
        if (updatedUser.isEmpty())
            throw new UserNotFoundException();
        return updatedUser.get();
    }
}
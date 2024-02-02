package com.EventHorizon.EventHorizon.RepositoryServices.User;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.*;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType.*;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserRepositoryService {

    @Autowired
    private UserRepository userRepository;

    public User getById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return this.getUserFromOptional(userOptional);
    }

    public Client getClientById(int id) {
        User user = this.getById(id);
        if (user.getRole() != Role.CLIENT)
            throw new NotClientException();
        return (Client) user;
    }

    public Organizer getOrganizerById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.ORGANIZER)
            throw new NotOrganizerException();
        return (Organizer) user;
    }

    public Admin getAdminById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.ADMIN)
            throw new NotAdminException();
        return (Admin) user;
    }

    public Sponsor getSponsorById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.SPONSOR)
            throw new NotSponsorException();
        return (Sponsor) user;
    }

    public Moderator getModeratorById(int id) {
        User user = getById(id);
        if (user.getRole() != Role.MODERATOR)
            throw new NotModeratorException();
        return (Moderator) user;
    }

    public User getByEmail(String email) {
        Optional<User> updatedUser = userRepository.findByEmail(email);
        return this.getUserFromOptional(updatedUser);
    }

    public User getByUserName(String userName) {
        Optional<User> updatedUser = userRepository.findByUserName(userName);
        return this.getUserFromOptional(updatedUser);
    }

    private User getUserFromOptional(Optional<User> userOptional) {
        if (userOptional.isEmpty())
            throw new UserNotFoundException();
        return userOptional.get();
    }
}

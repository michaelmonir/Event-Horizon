package com.EventHorizon.EventHorizon.RepositoryServices.User;

import com.EventHorizon.EventHorizon.Entities.User.*;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType.*;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetUserRepositoryServiceTest {

    @Autowired
    private GetUserRepositoryService getUserRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getNormalUserTest(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepository.save(organizer);
        Organizer organizer1 = (Organizer) getUserRepositoryService.getById(organizer.getId());
        Assertions.assertEquals(organizer, organizer1);
    }

    @Test
    public void getOrganizer(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepository.save(organizer);
        Organizer organizer1 = getUserRepositoryService.getOrganizerById(organizer.getId());
        Assertions.assertEquals(organizer, organizer1);
    }

    @Test
    public void getClient(){
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        userRepository.save(client);
        Client client1 = getUserRepositoryService.getClientById(client.getId());
        Assertions.assertEquals(client, client1);
    }

//    @Test
//    public void getSponsor(){
//        Sponsor sponsor = (Sponsor) userCustomCreator.getUser(Role.SPONSOR);
//        sponsor.setSponsorSeatArchiveList(List.of(new SponsorSeatArchive()));
//        userRepository.save(sponsor);
//        Sponsor sponsor1 = getUserRepositoryService.getSponsorById(sponsor.getId());
//        Assertions.assertEquals(sponsor, sponsor1);
//    }

    @Test
    public void getAdmin() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
        userRepository.save(admin);
        Admin admin1 = getUserRepositoryService.getAdminById(admin.getId());
        Assertions.assertEquals(admin, admin1);
    }

    @Test
    public void getModerator() {
        Moderator moderator = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        userRepository.save(moderator);
        Moderator moderator1 = getUserRepositoryService.getModeratorById(moderator.getId());
        Assertions.assertEquals(moderator, moderator1);
    }

    @Test
    public void userNotFound() {
        Assertions.assertThrows(UserNotFoundException.class, () -> getUserRepositoryService.getById(10000007));
    }

    @Test
    public void NotOrganizer() {
        Sponsor sponsor = (Sponsor) userCustomCreator.getUser(Role.SPONSOR);
        userRepository.save(sponsor);
        Assertions.assertThrows(NotOrganizerException.class
                , () -> getUserRepositoryService.getOrganizerById(sponsor.getId()));
    }

    @Test
    public void NotClient() {
        Sponsor sponsor = (Sponsor) userCustomCreator.getUser(Role.SPONSOR);
        userRepository.save(sponsor);
        Assertions.assertThrows(NotClientException.class
                , () -> getUserRepositoryService.getClientById(sponsor.getId()));
    }

    @Test
    public void NotSponsor() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        userRepository.save(client);
        Assertions.assertThrows(NotSponsorException.class
                , () -> getUserRepositoryService.getSponsorById(client.getId()));
    }

    @Test
    public void NotAdmin() {
        Moderator moderator = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        userRepository.save(moderator);
        Assertions.assertThrows(NotAdminException.class
                , () -> getUserRepositoryService.getAdminById(moderator.getId()));
    }

    @Test
    public void NotModerator() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
        userRepository.save(admin);
        Assertions.assertThrows(NotModeratorException.class
                , () -> getUserRepositoryService.getModeratorById(admin.getId()));
    }

    @Test
    public void getByEmail() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        userRepository.save(client);
        Client client1 = (Client) getUserRepositoryService.getByEmail(client.getEmail());
        Assertions.assertEquals(client, client1);
    }

    @Test
    public void getByUserName() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        userRepository.save(client);
        Client client1 = (Client) getUserRepositoryService.getByUserName(client.userName);
        Assertions.assertEquals(client, client1);
    }
}






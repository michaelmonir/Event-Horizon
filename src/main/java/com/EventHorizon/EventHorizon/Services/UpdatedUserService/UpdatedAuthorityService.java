//package com.EventHorizon.EventHorizon.Services.UpdatedUserService;
//
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
//import com.EventHorizon.EventHorizon.Entities.enums.Gender;
//import com.EventHorizon.EventHorizon.Entities.enums.Role;
//import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotModeratorOperationsException;
//import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UpdatedAuthorityService {
//
//    @Autowired
//    InformationRepositoryService informationRepositoryService;
//    @Autowired
//    LaunchedEventRepositoryService launchedEventRepositoryService;
//    @Autowired
//    UpdatedUserRepositoryService updatedUserRepositoryService;
//    public void deleteUser(int id) {
//        Information information = informationRepositoryService.getByID(idOfInformation);
//        if (information.getRole() == Role.MODERATOR)
//            throw new NotModeratorOperationsException();
//        informationRepositoryService.delete(idOfInformation);
//    }
//
//    public void deleteEvent(int idOfEvent) {
//        launchedEventRepositoryService.delete(idOfEvent);
//    }
//
//    public List<? extends User> getAllUsersByRole(Role role) {
//        UserInformationRepositoryService myService = informationRepositoryService.getService(role);
//        return myService.findAllOfUsers();
//    }
//
//    public List<User> getAllUsersByFirstName(String firstName) {
//        List<Information> informationList = informationRepositoryService.getByFirstName(firstName);
//        List<User> userList = new ArrayList<>();
//        for (Information information : informationList) {
//            User user = informationRepositoryService.getUserByInformation(information);
//            userList.add(user);
//        }
//        return userList;
//    }
//
//    public List<User> getAllUsersByLastName(String lastName) {
//        List<Information> informationList = informationRepositoryService.getByLastName(lastName);
//        List<User> userList = new ArrayList<>();
//        for (Information information : informationList) {
//            User user = informationRepositoryService.getUserByInformation(information);
//            userList.add(user);
//        }
//        return userList;
//    }
//
//    public List<User> getAllUsersByGender(Gender gender) {
//        List<Information> informationList = informationRepositoryService.getByGender(gender);
//        List<User> userList = new ArrayList<>();
//        for (Information information : informationList) {
//            User user = informationRepositoryService.getUserByInformation(information);
//            userList.add(user);
//        }
//        return userList;
//    }
//
//    public User getUserByEmail(String email) {
//        Information information = informationRepositoryService.getByEmail(email);
//        return informationRepositoryService.getUserByInformation(information);
//    }
//
//    public User getUserByUserName(String userName) {
//        Information information = informationRepositoryService.getByUserName(userName);
//        return informationRepositoryService.getUserByInformation(information);
//    }
//}

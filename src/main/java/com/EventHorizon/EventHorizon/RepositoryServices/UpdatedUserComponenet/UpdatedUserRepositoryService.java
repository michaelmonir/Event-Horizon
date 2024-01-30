package com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UpdatedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatedUserRepositoryService {

    private final UpdatedUserRepository updatedUserRepository;
    private final UpdatedUserRepositoryFactory updatedUserRepositoryFactory;
    public void add(UpdatedUser updatedUser) {
        JpaRepository jpaRepository = updatedUserRepositoryFactory.getRepository(updatedUser);
        jpaRepository.save(updatedUser);
    }
    public void delete(UpdatedUser updatedUser) {
        updatedUserRepository.delete(updatedUser);
    }
    public void deleteById(int id) {
        updatedUserRepository.deleteById(id);
    }
    public void update( UpdatedUser updatedUser) {
        updatedUserRepository.save(updatedUser);
    }
}

package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
}

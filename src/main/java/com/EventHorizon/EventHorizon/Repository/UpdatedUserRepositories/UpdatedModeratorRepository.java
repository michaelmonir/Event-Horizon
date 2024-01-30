package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedModerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedModeratorRepository extends JpaRepository<UpdatedModerator, Integer> {
}

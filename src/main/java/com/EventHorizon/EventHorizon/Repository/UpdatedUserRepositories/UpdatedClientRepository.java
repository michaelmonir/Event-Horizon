package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedClientRepository extends JpaRepository<UpdatedClient,Integer> {
}

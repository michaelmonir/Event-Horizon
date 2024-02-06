package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.User.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
}

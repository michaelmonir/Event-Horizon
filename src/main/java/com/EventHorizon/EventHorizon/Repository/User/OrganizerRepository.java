package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}

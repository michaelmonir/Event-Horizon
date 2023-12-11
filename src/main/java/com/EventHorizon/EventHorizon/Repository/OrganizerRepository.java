package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    Organizer findByInformation(Information information);

    @Override
    List<Organizer> findAll();
}
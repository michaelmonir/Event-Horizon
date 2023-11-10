package com.EventHorizon.EventHorizon.repository;

import com.EventHorizon.EventHorizon.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

}
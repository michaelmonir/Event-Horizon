package com.EventHorizon.EventHorizon.repository;

import com.EventHorizon.EventHorizon.entity.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

            
}
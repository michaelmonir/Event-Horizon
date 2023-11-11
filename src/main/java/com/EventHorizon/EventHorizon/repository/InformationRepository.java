package com.EventHorizon.EventHorizon.repository;

import com.EventHorizon.EventHorizon.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {
    Optional<Information> findByEmail(String email);

}
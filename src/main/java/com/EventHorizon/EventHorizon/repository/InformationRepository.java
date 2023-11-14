package com.EventHorizon.EventHorizon.repository;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InformationRepository extends JpaRepository<Information, Integer> {

   Information findByEmail(String email);

   Information findByUserName(String username);

   List<Information> findByLastName(String lastname);

   List<Information> findByFirstName(String firstname);

   List<Information> findByGender(String gender);

   List<Information> findByRole(String role);

   List<Information> findBySignInWithEmail(int value);

}
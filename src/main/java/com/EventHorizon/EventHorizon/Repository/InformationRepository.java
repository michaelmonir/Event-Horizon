package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import org.springframework.data.jpa.repository.JpaRepository;
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
package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
}
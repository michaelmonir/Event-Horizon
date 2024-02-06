package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.User.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}

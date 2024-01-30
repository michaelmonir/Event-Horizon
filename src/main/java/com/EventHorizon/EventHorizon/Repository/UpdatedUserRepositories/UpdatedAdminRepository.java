package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedAdminRepository extends JpaRepository<UpdatedAdmin,Integer> {
}

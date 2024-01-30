package com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpdatedUserRepository extends JpaRepository<UpdatedUser, Integer> {
    @Query("SELECT u.role FROM UpdatedUser u WHERE u.id = :userId")
    Optional<Role> findRoleById(@Param("userId") Integer userId);
}

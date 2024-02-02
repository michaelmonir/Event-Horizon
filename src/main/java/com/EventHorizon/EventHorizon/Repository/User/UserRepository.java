package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.role FROM User u WHERE u.id = :userId")
    Optional<Role> findRoleById(@Param("userId") Integer userId);
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<? extends User> findAllByRole(@Param("role") Role role);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String username);

    boolean existsById(int id);
}

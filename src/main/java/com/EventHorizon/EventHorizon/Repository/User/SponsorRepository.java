package com.EventHorizon.EventHorizon.Repository.User;

import com.EventHorizon.EventHorizon.Entities.User.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor,Integer> {
}

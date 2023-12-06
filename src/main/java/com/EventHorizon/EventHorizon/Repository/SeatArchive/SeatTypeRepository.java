package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType,Integer> {
}

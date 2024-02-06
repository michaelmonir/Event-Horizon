package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.Event.AdsOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsOptionRepository extends JpaRepository<AdsOption, Integer>
{
}

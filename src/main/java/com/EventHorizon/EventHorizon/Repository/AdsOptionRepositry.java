package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsOptionRepositry extends JpaRepository<AdsOption, Integer>
{
}

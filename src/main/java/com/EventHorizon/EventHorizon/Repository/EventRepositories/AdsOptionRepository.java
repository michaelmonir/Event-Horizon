package com.EventHorizon.EventHorizon.Repository.EventRepositories;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsOptionRepository extends JpaRepository<AdsOption, Integer>
{
}

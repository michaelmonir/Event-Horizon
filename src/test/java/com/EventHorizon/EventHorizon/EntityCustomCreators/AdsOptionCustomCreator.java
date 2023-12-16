package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdsOptionCustomCreator
{
    @Autowired
    AdsOptionRepository adsOptionRepositry;

    int numOfCreatedObjects = 0;

    public AdsOption getAdsOption()
    {
        numOfCreatedObjects++;

        AdsOption adsOption = AdsOption.builder()
                .name("adsOption" + numOfCreatedObjects)
                .priority(numOfCreatedObjects)
                .build();
        return adsOption;
    }
}

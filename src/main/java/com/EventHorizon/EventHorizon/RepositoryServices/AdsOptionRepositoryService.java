package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.DTOs.AdsOptionDTO;
import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdsOptionRepositoryService
{
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public AdsOption getAdsOptionFromDTO(AdsOptionDTO adsOptionDTO)
    {
        Optional<AdsOption> optionalAdsOption = this.adsOptionRepositry.findById(adsOptionDTO.id);
        if (!optionalAdsOption.isPresent())
            throw new AdsOptionNotFoundException();
        return optionalAdsOption.get();
    }

    public AdsOptionDTO getDTOFromAdsOption(AdsOption adsOption)
    {
        AdsOptionDTO adsOptionDTO = new AdsOptionDTO();
        adsOptionDTO.id = adsOption.getId();
        adsOptionDTO.name = adsOption.getName();
        return adsOptionDTO;
    }
}

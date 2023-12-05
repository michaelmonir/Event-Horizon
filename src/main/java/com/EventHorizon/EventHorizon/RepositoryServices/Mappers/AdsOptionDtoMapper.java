package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdsOptionDtoMapper {

    @Autowired
    AdsOptionRepositry adsOptionRepositry;
    public AdsOption getAdsOptionFromDTO(AdsOptionDto adsOptionDTO)
    {
        Optional<AdsOption> optionalAdsOption = this.adsOptionRepositry.findById(adsOptionDTO.id);
        if (!optionalAdsOption.isPresent())
            throw new AdsOptionNotFoundException();
        return optionalAdsOption.get();
    }
}

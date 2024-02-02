package com.EventHorizon.EventHorizon.Mappers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdsOptionDtoMapper {

    @Autowired
    AdsOptionRepository adsOptionRepository;
    public AdsOption getAdsOptionFromDTO(AdsOptionDto adsOptionDTO)
    {
        Optional<AdsOption> optionalAdsOption = this.adsOptionRepository.findById(adsOptionDTO.id);
        if (!optionalAdsOption.isPresent())
            throw new AdsOptionNotFoundException();
        return optionalAdsOption.get();
    }

}

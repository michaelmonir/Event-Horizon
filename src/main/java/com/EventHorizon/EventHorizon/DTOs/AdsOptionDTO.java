package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdsOptionDTO
{
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public int id;
    public String name;

    public AdsOptionDTO(AdsOption adsOption)
    {
        this.id = adsOption.getId();
        this.name = adsOption.getName();
    }

    public AdsOption getAdsOption()
    {
        Optional<AdsOption> optionalAdsOption = this.adsOptionRepositry.findById(this.id);
        if (!optionalAdsOption.isPresent())
            throw new AdsOptionNotFoundException();
        return optionalAdsOption.get();
    }
}

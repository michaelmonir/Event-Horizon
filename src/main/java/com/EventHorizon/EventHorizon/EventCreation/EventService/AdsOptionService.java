package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Exceptions.AdsAlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.AdsNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class AdsOptionService {
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public AdsOption saveAdsOptionWhenCreating(AdsOption adsOption) {
        if (adsOption.getId() != 0)
            throw new AdsAlreadyFoundException();

        adsOptionRepositry.save(adsOption);
        return adsOption;
    }

    public AdsOption updateAdsOption(int id, AdsOption adsOption) {
        Optional<AdsOption> optionalOldAds = adsOptionRepositry.findById(id);

        if (adsOption.getId() != 0)
            throw new AdsAlreadyFoundException();

        if (!optionalOldAds.isPresent())
            throw new AdsNotFoundException();


        adsOption.setId(id);
        adsOptionRepositry.save(adsOption);
        return adsOption;
    }

    public AdsOption findAdsOptionById(int id) {
        Optional<AdsOption> adsOption = adsOptionRepositry.findById(id);


        if (!adsOption.isPresent())
            throw new AdsNotFoundException();

        return adsOption.get();

    }
}

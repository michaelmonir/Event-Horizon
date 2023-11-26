package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionAlreadyExistException;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdsOptionRepositoryService {
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public AdsOption saveAdsOptionWhenCreating(AdsOption adsOption) {
        if (adsOption.getId() != 0)
            throw new AdsOptionAlreadyExistException();

        adsOptionRepositry.save(adsOption);
        return adsOption;
    }

    public AdsOption updateAdsOption(int id, AdsOption adsOption) {
        Optional<AdsOption> optionalOldAds = adsOptionRepositry.findById(id);

        if (adsOption.getId() != 0)
            throw new AdsOptionAlreadyExistException();

        if (!optionalOldAds.isPresent())
            throw new AdsOptionNotFoundException();


        adsOption.setId(id);
        adsOptionRepositry.save(adsOption);
        return adsOption;
    }

    public AdsOption findAdsOptionById(int id) {
        Optional<AdsOption> adsOption = adsOptionRepositry.findById(id);


        if (!adsOption.isPresent())
            throw new AdsOptionNotFoundException();

        return adsOption.get();
    }


}

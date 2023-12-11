package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionAlreadyExistException;
import com.EventHorizon.EventHorizon.Exceptions.AdsOptionExceptions.AdsOptionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdsOptionRepositoryService {
    @Autowired
    private AdsOptionRepository adsOptionRepository;

    public AdsOption saveAdsOptionWhenCreating(AdsOption adsOption) {
        if (adsOption.getId() != 0)
            throw new AdsOptionAlreadyExistException();

        adsOptionRepository.save(adsOption);
        return adsOption;
    }

    public AdsOption updateAdsOption(int id, AdsOption adsOption) {
        Optional<AdsOption> optionalOldAds = adsOptionRepository.findById(id);

        if (adsOption.getId() != 0)
            throw new AdsOptionAlreadyExistException();

        if (!optionalOldAds.isPresent())
            throw new AdsOptionNotFoundException();


        adsOption.setId(id);
        adsOptionRepository.save(adsOption);
        return adsOption;
    }

    public AdsOption findAdsOptionById(int id) {
        Optional<AdsOption> adsOption = adsOptionRepository.findById(id);


        if (!adsOption.isPresent())
            throw new AdsOptionNotFoundException();

        return adsOption.get();
    }


}

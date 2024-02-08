package com.EventHorizon.EventHorizon.RepositoryServices.Event;

import com.EventHorizon.EventHorizon.Entities.Event.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.Event.AdsOption.AdsOptionAlreadyExistException;
import com.EventHorizon.EventHorizon.Exceptions.Event.AdsOption.AdsOptionNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdsOptionRepositoryServiceTest {
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        AdsOption adsOption = new AdsOption();
        adsOption.setId(5);
        adsOption.setName("Sample Ads Option");

        Assertions.assertThrows(AdsOptionAlreadyExistException.class, () -> {
            adsOptionRepositoryService.saveAdsOptionWhenCreating(adsOption);
        });
    }

    @Test
    public void addAdsOptionNotGettingError() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();

        Assertions.assertDoesNotThrow(() -> {
            adsOptionRepositoryService.saveAdsOptionWhenCreating(adsOption);
            Assertions.assertNotEquals(0, adsOption.getId());
        });
    }

    @Test
    public void editAdsOptionGettingErrorAdsNotFoundException() {
        Assertions.assertThrows(AdsOptionNotFoundException.class, () -> {
            adsOptionRepositoryService.updateAdsOption(0, new AdsOption());
        });
    }

    @Test
    public void editAdsOptionGettingErrorAdsAlreadyFoundException() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();
        adsOptionRepositoryService.saveAdsOptionWhenCreating(adsOption);

        Assertions.assertThrows(AdsOptionAlreadyExistException.class, () -> {
            adsOptionRepositoryService.updateAdsOption(34, adsOption);
        });
    }

    @Test
    public void editAdsOptionWithoutError() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();
        adsOptionRepositoryService.saveAdsOptionWhenCreating(adsOption);

        AdsOption newAdsOption = AdsOption.builder()
                .name("Updated Ads Option")
                .priority(3)
                .build();

        Assertions.assertDoesNotThrow(() -> {
            adsOptionRepositoryService.updateAdsOption(adsOption.getId(), newAdsOption);
            AdsOption updatedAdsOption = adsOptionRepositoryService.getById(adsOption.getId());
            Assertions.assertNotNull(updatedAdsOption);
            Assertions.assertEquals(newAdsOption.getName(), updatedAdsOption.getName());
            Assertions.assertEquals(newAdsOption.getPriority(), updatedAdsOption.getPriority());
        });
    }
}
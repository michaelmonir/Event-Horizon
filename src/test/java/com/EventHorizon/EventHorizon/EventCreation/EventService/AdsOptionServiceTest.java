package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsAlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.AdsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdsOptionServiceTest {
    @Autowired
    private AdsOptionService adsOptionService;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        AdsOption adsOption = new AdsOption();
        adsOption.setId(5);
        adsOption.setName("Sample Ads Option");

        Assertions.assertThrows(AdsAlreadyFoundException.class, () -> {
            adsOptionService.saveAdsOptionWhenCreating(adsOption);
        });
    }

    @Test
    public void addAdsOptionNotGettingError() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();

        Assertions.assertDoesNotThrow(() -> {
            adsOptionService.saveAdsOptionWhenCreating(adsOption);
            Assertions.assertNotEquals(0, adsOption.getId());
        });
    }

    @Test
    public void editAdsOptionGettingErrorAdsNotFoundException() {
        Assertions.assertThrows(AdsNotFoundException.class, () -> {
            adsOptionService.updateAdsOption(0, new AdsOption());
        });
    }

    @Test
    public void editAdsOptionGettingErrorAdsAlreadyFoundException() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();
        adsOptionService.saveAdsOptionWhenCreating(adsOption);

        Assertions.assertThrows(AdsAlreadyFoundException.class, () -> {
            adsOptionService.updateAdsOption(34, adsOption);
        });
    }

    @Test
    public void editAdsOptionWithoutError() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();
        adsOptionService.saveAdsOptionWhenCreating(adsOption);

        AdsOption newAdsOption = AdsOption.builder()
                .name("Updated Ads Option")
                .priority(3)
                .build();

        Assertions.assertDoesNotThrow(() -> {
            adsOptionService.updateAdsOption(adsOption.getId(), newAdsOption);
            AdsOption updatedAdsOption = adsOptionService.findAdsOptionById(adsOption.getId());
            Assertions.assertNotNull(updatedAdsOption);
            Assertions.assertEquals(newAdsOption.getName(), updatedAdsOption.getName());
            Assertions.assertEquals(newAdsOption.getPriority(), updatedAdsOption.getPriority());
        });
    }
}
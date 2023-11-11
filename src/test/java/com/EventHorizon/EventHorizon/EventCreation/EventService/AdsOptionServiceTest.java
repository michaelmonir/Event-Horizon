package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.Exceptions.AdsAlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.AdsNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdsOptionServiceTest {
    @Autowired
    private AdsOptionService adsOptionService;


    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        AdsOption adsOption = new AdsOption();
        adsOption.setId(5);
        adsOption.setName("Sample Ads Option");

        try {
            adsOptionService.saveAdsOptionWhenCreating(adsOption);
            Assertions.fail();
        } catch (AdsAlreadyFoundException e) {
            // Expected behavior
        }
    }

    @Test
    public void addAdsOptionNotGettingError() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();

        try {
            adsOptionService.saveAdsOptionWhenCreating(adsOption);
            Assertions.assertNotEquals(0, adsOption.getId());
        } catch (AdsAlreadyFoundException e) {
            Assertions.fail("Unexpected exception");
        }
    }

    @Test
    public void editAdsOptionGettingErrorAdsNotFoundException() {
        try {
            adsOptionService.updateAdsOption(0, new AdsOption());
            Assertions.fail();
        } catch (AdsNotFoundException e) {
            // Expected behavior
        }
    }

    @Test
    public void editAdsOptionGettingErrorAdsAlreadyFoundException() {
        AdsOption adsOption = AdsOption.builder()
                .name("Sample Ads Option")
                .priority(2)
                .build();
        adsOptionService.saveAdsOptionWhenCreating(adsOption);

        try {
            adsOptionService.updateAdsOption(34, adsOption);
            Assertions.fail();
        } catch (AdsAlreadyFoundException e) {
            // Expected behavior
        }
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

        try {
            adsOptionService.updateAdsOption(adsOption.getId(), newAdsOption);
            AdsOption updatedAdsOption = adsOptionService.findAdsOptionById(adsOption.getId());
            Assertions.assertNotNull(updatedAdsOption);
            Assertions.assertEquals(newAdsOption.getName(), updatedAdsOption.getName());
            Assertions.assertEquals(newAdsOption.getPriority(), updatedAdsOption.getPriority());
        } catch (AdsNotFoundException | AdsAlreadyFoundException e) {
            Assertions.fail("Unexpected exception");
        }
    }

}
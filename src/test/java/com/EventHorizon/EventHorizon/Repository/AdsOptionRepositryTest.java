package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class AdsOptionRepositryTest {
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    @Test
    public void createAdsOption() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepositry.save(adsOption);
        Assertions.assertNotEquals(0, adsOption.getId());
    }

    @Test
    public void findNotExistedAdsById() {
        Optional<AdsOption> adsOption = adsOptionRepositry.findById(0);
        Assertions.assertEquals(adsOption.isPresent(), false);
    }

    @Test
    public void findExistedAdsById() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepositry.save(adsOption);
        Optional<AdsOption> findedEvent = adsOptionRepositry.findById(adsOption.getId());
        Assertions.assertEquals(findedEvent.isPresent(), true);
    }

    @Test
    public void CreateAdsOptionWithoutName() {

        AdsOption adsOption = AdsOption.builder()
                .priority(1).build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            adsOptionRepositry.save(adsOption);
        });
    }

}
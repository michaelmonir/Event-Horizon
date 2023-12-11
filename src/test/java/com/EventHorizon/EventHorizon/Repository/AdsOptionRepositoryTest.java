package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class AdsOptionRepositoryTest {
    @Autowired
    private AdsOptionRepository adsOptionRepository;

    @Test
    public void createAdsOption() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepository.save(adsOption);
        Assertions.assertNotEquals(0, adsOption.getId());
    }

    @Test
    public void findNotExistedAdsById() {
        Optional<AdsOption> adsOption = adsOptionRepository.findById(0);
        Assertions.assertFalse(adsOption.isPresent());
    }

    @Test
    public void findExistedAdsById() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepository.save(adsOption);
        Optional<AdsOption> findedEvent = adsOptionRepository.findById(adsOption.getId());
        Assertions.assertTrue(findedEvent.isPresent());
    }

    @Test
    public void CreateAdsOptionWithoutName() {

        AdsOption adsOption = AdsOption.builder()
                .priority(1).build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            adsOptionRepository.save(adsOption);
        });
    }

}
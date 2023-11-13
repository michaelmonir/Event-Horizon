package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.AdsOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdsOptionRepositryTest {
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Test
    public void saveAdsOption(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(1).build();
        adsOptionRepositry.save(adsOption);
    }


}
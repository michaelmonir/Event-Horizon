package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocationRepositoryTest {
@Autowired
    private LocationRepository locationRepository;
@Test
    public void saveLocation(){
    Location location1= Location.builder().country("Egypt").city("Alex").build();
    locationRepository.save(location1);
}


}
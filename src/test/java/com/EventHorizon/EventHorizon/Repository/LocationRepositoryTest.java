package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.Location;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationRepositoryTest {
@Autowired
    private LocationRepository locationRepository;
    @Test
    public void saveLocation() {
        Location location1 = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        locationRepository.save(location1);
        Assertions.assertNotEquals(0,location1.getId());
    }
    @Test
    public void findNotExistedAdsById(){
        Optional<Location> location=locationRepository.findById(0);
        Assertions.assertEquals(location.isPresent(),false);
    }
    @Test
    public void findExistedAdsById(){
        Location location1 = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        locationRepository.save(location1);
        Assertions.assertNotEquals(0,location1.getId());
        Optional<Location> findedLocation=locationRepository.findById(location1.getId());
        Assertions.assertEquals(findedLocation.isPresent(),true);
    }
    @Test
    public void createLocationWithoutCity() {

        Location location = Location.builder()
                .country("Egypt").build();


        Assertions.assertThrows(RuntimeException.class, () -> {
            locationRepository.save(location);
        });
    }
    @Test
    public void createLocationWithoutCountry() {

        Location location = Location.builder()
                .city("Alex").build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            locationRepository.save(location);
        });
    }


}
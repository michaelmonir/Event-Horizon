package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationCustomCreator
{
    @Autowired
    private LocationRepository locationRepository;

    int numberOfCreatedObjects = 0;

    Location getLocation()
    {
        numberOfCreatedObjects++;

        Location location = Location.builder()
                .country("Country" + this.numberOfCreatedObjects)
                .city("Cairo")
                .build();
        return location;
    }
}

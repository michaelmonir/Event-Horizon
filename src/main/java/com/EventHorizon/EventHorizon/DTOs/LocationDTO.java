package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Location;

public class LocationDTO
{
    public String country;
    public String city;
    public String address;

    public Location getLocation()
    {
        Location location = new Location(country, city, address);
        return location;
    }
}

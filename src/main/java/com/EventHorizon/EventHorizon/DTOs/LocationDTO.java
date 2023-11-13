package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Location;

public class LocationDTO
{
    public String country;
    public String city;
    public String address;

    public LocationDTO(Location location)
    {
        this.country = location.getCountry();
        this.city = location.getCity();
        this.address = location.getAddress();
    }

    public Location getLocation()
    {
        Location location = new Location(country, city, address);
        return location;
    }
}

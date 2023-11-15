package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Location;
import lombok.EqualsAndHashCode;

import java.util.Date;

//@Builder
@EqualsAndHashCode
//@NoArgsConstructor
public class DetailedEventDTO
{
    public int id;
    public String name;
    public String description;
    public String eventCategory;
    public Date eventDate;
    public AdsOptionDTO eventAds;
    public Location eventLocation;
}

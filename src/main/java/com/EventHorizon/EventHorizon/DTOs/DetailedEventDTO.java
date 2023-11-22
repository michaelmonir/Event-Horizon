package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Location;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode
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

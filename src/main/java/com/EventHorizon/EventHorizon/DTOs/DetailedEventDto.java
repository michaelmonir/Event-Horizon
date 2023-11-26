package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Location;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedEventDto
{
    public int id;
    public String name;
    public String description;
    public String eventCategory;
    public Date eventDate;
    public AdsOptionDto eventAds;
    public Location eventLocation;
}

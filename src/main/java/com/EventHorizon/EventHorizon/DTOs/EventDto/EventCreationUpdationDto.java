package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.SeatArchiveDtos.SeatTypeDto;
import com.EventHorizon.EventHorizon.Entities.Event.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class EventCreationUpdationDto
{
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private int adsOptionId;
    private Location eventLocation;
    private List<SeatTypeDto> seatTypes;
}
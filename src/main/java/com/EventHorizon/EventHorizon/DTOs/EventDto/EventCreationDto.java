package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
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
public class EventCreationDto
{
    protected int id;
    protected String name;
    protected String description;
    protected String eventCategory;
    protected Date eventDate;
    protected int AdsOptionId;
    protected Location eventLocation;
    protected List<SeatTypeDto> seatTypes;
}
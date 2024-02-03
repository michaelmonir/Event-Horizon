package com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos;


import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.SeatArchiveDtos.SeatTypeDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class EventViewDto
{
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private EventType eventType;
    private AdsOptionDto eventAds;
    private Location eventLocation;
    private OrganizerHeaderDto eventOrganizer;
    private List<SeatTypeDto> seatTypes;
    private Date launchedDate;
    private UserEventRole userEventRole;
}

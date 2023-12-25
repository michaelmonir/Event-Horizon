package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Mappers.SeatTypeListMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ViewEventDto {

    @Autowired
    private SeatTypeListMapper seatTypeListMapper;

    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;
    private OrganizerHeaderDto eventOrganizer;
    private List<SeatTypeDto> seatTypes;

    @Override
    public String toString() {
        return "EventDetailsDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventCategory='" + eventCategory + '\'' +
                ", eventDate=" + eventDate +
                ", eventLocation=" + eventLocation +
                '}';
    }
}

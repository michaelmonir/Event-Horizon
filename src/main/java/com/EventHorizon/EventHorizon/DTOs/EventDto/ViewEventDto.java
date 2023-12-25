package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ViewEventDto {
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;
    private OrganizerHeaderDto eventOrganizer;
    private List<SeatTypeDto> seatTypes;


    public ViewEventDto(LaunchedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();
        this.description = event.getDescription();
        this.eventLocation = event.getEventLocation();
        this.eventOrganizer = new OrganizerHeaderDto(event.getEventOrganizer());
        this.initializeSeatTypeListFromEvent(event);
    }

    private void initializeSeatTypeListFromEvent(Event event){
        this.seatTypes = new ArrayList<>();
        event.getSeatTypes().forEach(seatType -> {
            this.seatTypes.add(new SeatTypeDto(seatType));
        });
    }

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

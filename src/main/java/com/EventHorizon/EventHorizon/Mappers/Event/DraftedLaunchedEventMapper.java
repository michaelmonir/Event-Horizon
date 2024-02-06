package com.EventHorizon.EventHorizon.Mappers.Event;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import org.springframework.stereotype.Service;

@Service
public class DraftedLaunchedEventMapper {

    public LaunchedEvent getLaunchedEventFromDraftedEvent(DraftedEvent draftedEvent){
        return LaunchedEvent.builder()
                .id(draftedEvent.getId())
                .name(draftedEvent.getName())
                .description(draftedEvent.getDescription())
                .eventCategory(draftedEvent.getEventCategory())
                .eventDate(draftedEvent.getEventDate())
                .eventLocation(draftedEvent.getEventLocation())
                .eventAds(draftedEvent.getEventAds())
                .eventType(EventType.LAUNCHEDEVENT)
                .eventOrganizer(draftedEvent.getEventOrganizer())
                .seatTypes(draftedEvent.getSeatTypes())
                .build();
    }
}

package com.EventHorizon.EventHorizon.Mappers;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
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
                .launchedDate(DateFunctions.getCurrentDate())
                .eventLocation(draftedEvent.getEventLocation())
                .eventAds(draftedEvent.getEventAds())
                .eventType(EventType.LAUNCHEDEVENT)
                .eventOrganizer(draftedEvent.getEventOrganizer())
                .seatTypes(draftedEvent.getSeatTypes())
                .build();
    }
}

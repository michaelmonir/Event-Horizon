package com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatTypeWithEventCustomCreator
{
    @Autowired
    private SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;


    public SeatType getAndCreateCustomSeatTypeFromSavedEvent() {
        SeatType customSeatType = this.seatTypeCustomCreator.getSeatType();
        DraftedEvent customEvent = (DraftedEvent) this.eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        List<SeatType> seatTypesList = new ArrayList<>();
        seatTypesList.add(customSeatType);

        customEvent.setSeatTypes(seatTypesList);
        customEvent = this.draftedEventRepositoryService.saveWhenCreating(customEvent);

        return customEvent.getSeatTypes().get(0);
    }
}

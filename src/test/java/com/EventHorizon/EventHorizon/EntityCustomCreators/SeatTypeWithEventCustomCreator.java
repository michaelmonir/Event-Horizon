package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatTypeWithEventCustomCreator
{
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    DraftedEventRepositoryService draftedEventRepositoryService;


    public SeatType getAndCreateCustomSeatTypeFromSavedEvent() {
        SeatType customSeatType = this.seatTypeCustomCreator.getSeatType();
        Event customEvent = this.eventCustomCreator.getLaunchedEvent();
        List<SeatType> seatTypesList = new ArrayList<>();
        seatTypesList.add(customSeatType);

        customEvent.setSeatTypes(seatTypesList);
        customEvent = this.launchedEventRepositoryService.saveWhenCreating(customEvent);

        return customEvent.getSeatTypes().get(0);
    }
}

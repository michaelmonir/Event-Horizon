package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
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
    EventRepositoryService eventRepositoryService;

    public SeatType getAndCreateCustomSeatTypeFromSavedEvent() {
        SeatType customSeatType = this.seatTypeCustomCreator.getSeatType();
        Event customEvent = this.eventCustomCreator.getEvent();
        List<SeatType> seatTypesList = new ArrayList<>();
        seatTypesList.add(customSeatType);

        customEvent.setSeatTypes(seatTypesList);
        customEvent = this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(customEvent);

        return customEvent.getSeatTypes().get(0);
    }
}

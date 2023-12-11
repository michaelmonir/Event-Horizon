package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.stereotype.Service;

@Service
public class EventSeatArchiveRepositoryService {

    public void setEventForItsSeatArchives(Event event)
    {
        for (SeatType seatType : event.getSeatTypes())
            seatType.setEvent(event);
    }
}

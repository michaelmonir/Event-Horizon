package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventSeatArchiveRepositoryService {

    public void setEventForItsSeatArchives(Event event)
    {

        for (SeatType seatType : event.getSeatTypes())
        {
            seatType.event = event;
        }
//        SeatType s = event.getSeatTypes().get(0);
//        s.event = event;
//        List<SeatType> seatTypesCopy = new ArrayList<>(event.getSeatTypes());
//        for (SeatType seatType : seatTypesCopy) {
//            seatType.event = event;
//        }
    }
}

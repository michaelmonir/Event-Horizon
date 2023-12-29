package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventSeatArchiveRepositoryService {

    @Autowired
    private OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;

    public void setAndSaveSeatArchivesForEvent(Event event) {
        List<SeatType> seatTypes = event.getSeatTypes();
        for (SeatType seatType : seatTypes) {
            int num = seatType.getNumberOfSeats();
            OrganizerSeatArchive ora = new OrganizerSeatArchive(seatType, num, num);
            organizerSeatArchiveRepositoryService.save(ora);
        }
    }
}

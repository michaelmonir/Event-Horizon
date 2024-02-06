package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventAndSeatTypeAndSeatArchiveRepositoryService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;
    @Autowired
    private SeatTypeRepository seatTypeRepository;

    // it's important to delete the old seatTypes as they doesn't get deleted automatically
    void saveEventAndSeatTypeAndSeatArchive(DraftedEvent event) {
        seatTypeRepository.deleteByEventId(event.getId());
        for (SeatType seatType : event.getSeatTypes())
            seatType.setEvent(event);
        eventRepository.save(event);
        this.setOrganizerSeatArchives(event.getSeatTypes());
    }

    private void setOrganizerSeatArchives(List<SeatType> seatTypes) {
        for (SeatType seatType : seatTypes) {
            int num = seatType.getNumberOfSeats();
            OrganizerSeatArchive ora = new OrganizerSeatArchive(seatType, num, num);
            organizerSeatArchiveRepositoryService.save(ora);
        }
    }
}

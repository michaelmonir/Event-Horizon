package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventSeatTypesRepositoryService {

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    // it's important to delete the old seatTypes as they doesn't get deleted automatically
    public void setEventForItsSeatTypes(Event event) {
//        List<SeatType> l= seatTypeRepository.findAllByEventId(event.getId());
//        for(int i =0; i < l.size();i ++) {
//            l.get(i).setEvent(null);
//            seatTypeRepository.save(l.get(i));
//            seatTypeRepository.delete(l.get(i));
//        }
        seatTypeRepository.deleteByEventId(event.getId());
        for (SeatType seatType : event.getSeatTypes())
            seatType.setEvent(event);
    }
}

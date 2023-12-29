package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchives;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.EventSeatTypesRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EventSeatTypesRepositoryServiceTest {

    @InjectMocks
    EventSeatTypesRepositoryService eventSeatTypesRepositoryService;
    @Mock
    SeatTypeRepository seatTypeRepository;

    @Test
    public void setEventForItsSeatArchives() {
        SeatType s1 = new SeatType("s1", 1, 1), s2 = new SeatType("s2", 2, 2);
        List<SeatType> list = List.of(s1, s2);

        Event event = LaunchedEvent.builder().id(4).seatTypes(list).build();
        this.eventSeatTypesRepositoryService.setEventForItsSeatTypes(event);

        assertEquals(s1.getEvent(), event);
        assertEquals(s2.getEvent(), event);
    }
}

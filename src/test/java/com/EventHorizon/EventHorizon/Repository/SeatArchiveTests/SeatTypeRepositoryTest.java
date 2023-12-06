package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.*;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SeatTypeRepositoryTest
{
    @Autowired
    private InformationCustomCreator informationCustomCreator;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private SeatTypeRepository seatTypeRepository;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private AdsOptionCustomCreator adsOptionCustomCreator;

    @Test
    public void testCreatingSeatType()
    {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        Event event = this.eventCustomCreator.getEvent();
        event.setSeatTypes(seatTypes);

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }
}

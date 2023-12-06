package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
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
    private InformationCreator informationCreator;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @Test
    public void testCreatingSeatType()
    {
        Information information = informationCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location = Location.builder().country("qula").city("cairo").build();

        SeatType seatType = new SeatType("seattype1", 1);
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("e800")
                .eventOrganizer(organizer)
                .description("...")
                .seatTypes(seatTypes)
                .build();
        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }
}

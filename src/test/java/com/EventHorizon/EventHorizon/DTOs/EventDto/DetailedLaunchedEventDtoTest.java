package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DetailedLaunchedEventDtoTest {
    @Autowired
    private InformationCreator informationCreator;
    @Autowired
    private OrganizerRepository organizerRepository;

    private Organizer tempOrganizer;
    private AdsOption tempAdsOption;
    private Location tempLocation;
    private Event tempEvent;
    @Test
    public void testEventDetailsDtoConstructorMapsValuesCorrectly() {
        insialize();
        LaunchedEvent launchedEvent=LaunchedEvent.builder().event(tempEvent).build();
        DetailedLaunchedEventDto detailedLaunchedEventDto = new DetailedLaunchedEventDto(launchedEvent);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(launchedEvent.getName(), detailedLaunchedEventDto.getName());
        Assertions.assertEquals(launchedEvent.getDescription(), detailedLaunchedEventDto.getDescription());
        Assertions.assertEquals(launchedEvent.getEventCategory(), detailedLaunchedEventDto.getEventCategory());
        Assertions.assertEquals(launchedEvent.getEventDate(), detailedLaunchedEventDto.getEventDate());
        Assertions.assertEquals(launchedEvent.getEventLocation(), detailedLaunchedEventDto.getEventLocation());
        Assertions.assertEquals(launchedEvent.getLaunchedDate(), detailedLaunchedEventDto.getLaunchedDate());
    }
    private void insialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
    }

    private void createOrganizer() {
        Information information = informationCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        tempOrganizer = organizer;
    }

    public void createAdsOption() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        tempAdsOption = adsOption;

    }

    private void createEvent() {
        Event event = Event.builder()
                .eventAds(tempAdsOption)
                .eventLocation(tempLocation)
                .name("EventDetailsDtoTest")
                .description("...")
                .eventOrganizer(tempOrganizer)
                .eventCategory("Category1")
                .eventDate(new Date())
                .build();
        tempEvent = event;
    }

    private void createLocation() {
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        tempLocation = location;
    }

}
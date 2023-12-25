package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.Repository.UserRepositories.OrganizerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class EventDetailsDtoTest {

    @Autowired
    ViewEventDtoMapper viewEventDtoMapper;
    @Autowired
    private InformationCustomCreator informationCustomCreator;
    @Autowired
    private OrganizerRepository organizerRepository;

    private Organizer tempOrganizer;
    private AdsOption tempAdsOption;
    private Location tempLocation;
    private LaunchedEvent tempEvent;
    @Test
    public void getViewDtoFromLaunchedEvent() {
        insialize();
        ViewEventDto eventDetailsDto = viewEventDtoMapper.getDTOfromViewEvent(tempEvent);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(tempEvent.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(tempEvent.getDescription(), eventDetailsDto.getDescription());
        Assertions.assertEquals(tempEvent.getEventCategory(), eventDetailsDto.getEventCategory());
        Assertions.assertEquals(tempEvent.getEventDate(), eventDetailsDto.getEventDate());
        Assertions.assertEquals(tempEvent.getEventLocation(), eventDetailsDto.getEventLocation());
    }
    private void insialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
    }

    private void createOrganizer() {
        Information information = informationCustomCreator.getInformation(Role.ORGANIZER);
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
        tempEvent = LaunchedEvent.builder()
                .eventAds(tempAdsOption)
                .eventLocation(tempLocation)
                .name("EventDetailsDtoTest")
                .description("...")
                .eventOrganizer(tempOrganizer)
                .eventCategory("Category1")
                .eventDate(new Date())
                .seatTypes(new ArrayList<>())
                .build();
    }

    private void createLocation() {
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        tempLocation = location;
    }

}
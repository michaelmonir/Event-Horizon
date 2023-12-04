package com.EventHorizon.EventHorizon.Repository.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Role;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class EventHeaderDtoTest {
    @Autowired
    private InformationCreator informationCreator;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Test
    public void testEventHeaderDtoConstructorMapsValuesCorrectly() {
        Information information = informationCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();

        Location location = Location.builder().country("Egypt").city("Cairo").build();
        Date eventDate = new Date(); // Use an actual date here

        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("EventHeaderDtoTest")
                .eventCategory("Category1")
                .eventOrganizer(organizer)
                .build();

        EventHeaderDto eventHeaderDto = new EventHeaderDto(event);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(event.getName(), eventHeaderDto.getName());
        Assertions.assertEquals(event.getEventCategory(), eventHeaderDto.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), eventHeaderDto.getEventDate());
        Assertions.assertEquals(event.getEventLocation(), eventHeaderDto.getEventLocation());
    }


}
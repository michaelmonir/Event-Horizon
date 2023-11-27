package com.EventHorizon.EventHorizon.Repository.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class EventDetailsDtoTest {
    @Test
    public void testEventDetailsDtoConstructorMapsValuesCorrectly() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();

        Location location = Location.builder().country("Egypt").city("Cairo").build();
        Date eventDate = new Date(); // Use an actual date here

        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("EventDetailsDtoTest")
                .description("...")
                .eventCategory("Category1")
                .eventDate(eventDate)
                .build();

        ViewEventDto eventDetailsDto = new ViewEventDto(event);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(event.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(event.getDescription(), eventDetailsDto.getDescription());
        Assertions.assertEquals(event.getEventCategory(), eventDetailsDto.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), eventDetailsDto.getEventDate());
        Assertions.assertEquals(event.getEventLocation(), eventDetailsDto.getEventLocation());
    }

}
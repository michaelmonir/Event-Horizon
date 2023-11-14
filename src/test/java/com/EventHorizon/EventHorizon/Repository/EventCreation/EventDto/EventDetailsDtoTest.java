package com.EventHorizon.EventHorizon.Repository.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
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

        ViewEventDTO eventDetailsDto = new ViewEventDTO(event);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(event.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(event.getDescription(), eventDetailsDto.getDescription());
        Assertions.assertEquals(event.getEventCategory(), eventDetailsDto.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), eventDetailsDto.getEventDate());
        Assertions.assertEquals(event.getEventLocation(), eventDetailsDto.getEventLocation());
    }

}
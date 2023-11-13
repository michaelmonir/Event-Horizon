package com.EventHorizon.EventHorizon.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
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

        EventDetailsDto eventDetailsDto = new EventDetailsDto(event);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(event.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(event.getDescription(), eventDetailsDto.getDescription());
        Assertions.assertEquals(event.getEventCategory(), eventDetailsDto.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), eventDetailsDto.getEventDate());
        Assertions.assertEquals(event.getEventLocation(), eventDetailsDto.getEventLocation());
    }

}
package com.EventHorizon.EventHorizon.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventHeaderDtoTest {
    @Test
    public void testEventHeaderDtoConstructorMapsValuesCorrectly() {
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
                .build();

        EventHeaderDto eventHeaderDto = new EventHeaderDto(event);

        // Verify that the values are mapped correctly
        Assertions.assertEquals(event.getName(), eventHeaderDto.getName());
        Assertions.assertEquals(event.getEventCategory(), eventHeaderDto.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), eventHeaderDto.getEventDate());
        Assertions.assertEquals(event.getEventLocation(), eventHeaderDto.getEventLocation());
    }


}
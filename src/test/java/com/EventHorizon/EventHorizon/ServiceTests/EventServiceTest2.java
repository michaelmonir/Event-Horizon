package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.DTOs.LocationDTO;
import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventServiceTest2
{
    @Mock
    private EventRepositoryService eventRepositoryService;
    @InjectMocks
    private EventService eventService;
    @Mock
    private AdsOptionRepositry adsOptionRepositry;

    public Event getCustomEvent()
    {
        Event event = Event.builder()
                .id(1)
                .name("My Event")
                .eventLocation(new Location())
                .eventAds(this.getCustomAdsOptions())
                .build();
        return event;
    }

    public ViewEventDTO getCustomViewDTO()
    {
        ViewEventDTO expectedViewEventDTO = new ViewEventDTO();
        expectedViewEventDTO.name = "My Event";
        expectedViewEventDTO.eventLocationDTO = new LocationDTO();
        return expectedViewEventDTO;
    }

    public AdsOption getCustomAdsOptions() {
        AdsOption adsOption = new AdsOption();
        return adsOption;
    }

    @Before
    public void prepare()
    {
    }

    @Test
    public void gettingEventWithId()
    {
        AdsOption adsOption = this.getCustomAdsOptions();
        Event event = this.getCustomEvent();

        Mockito.when(this.eventRepositoryService.getEventAndHandleNotFound(Mockito.any(int.class)))
                .thenReturn(event);

        ViewEventDTO viewEventDTO = this.eventService.getEventForUser(1);
        ViewEventDTO expectedViewEventDTO = this.getCustomViewDTO();
        expectedViewEventDTO.id = 1;

        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDTO));
    }

}

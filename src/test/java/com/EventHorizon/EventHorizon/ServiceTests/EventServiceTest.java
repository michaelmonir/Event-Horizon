package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.DTOs.LocationDTO;
import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventService;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)


@SpringBootTest
public class EventServiceTest
{
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    public Event getCustomEvent()
    {
        Event event = Event.builder()
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
        this.adsOptionRepositry.save(adsOption);
        Event event = this.getCustomEvent();
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);

        ViewEventDTO viewEventDTO = this.eventService.getEventForUser(1);
        ViewEventDTO expectedViewEventDTO = this.getCustomViewDTO();
        expectedViewEventDTO.id = 1;

        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDTO));
    }

    @Test
    public void gettingEventWithId2()
    {
        AdsOption adsOption = this.getCustomAdsOptions();
        this.adsOptionRepositry.save(adsOption);
        Event event = this.getCustomEvent();
        event.setName("faris");
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);

        ViewEventDTO viewEventDTO = this.eventService.getEventForUser(1);
        ViewEventDTO expectedViewEventDTO = this.getCustomViewDTO();
        expectedViewEventDTO.id = 1;
        expectedViewEventDTO.name = "faris";

        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDTO));
    }

}

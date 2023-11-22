package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.DTOs.AdsOptionDTO;
import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.DTOs.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventServiceTest
{
    @Mock
    private EventRepositoryService eventRepositoryService;
    @InjectMocks
    private EventService eventService;

    private Event customEvent;
    private ViewEventDTO customViewEventDTO;
    private DetailedEventDTO customDetailedEventDTO;

    @Test
    public void gettingEventForUser() {
        this.initializeMocksAndCustomDTOs();

        ViewEventDTO viewEventDTO = this.eventService.getEventForUser(1);
        ViewEventDTO expectedViewEventDTO = this.customViewEventDTO;

        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDTO));
    }

    @Test
    public void gettingOrganizerEvent() {
        this.initializeMocksAndCustomDTOs();
        this.eventService.getEventForUser(1);
        DetailedEventDTO detailedEventDTO = this.eventService.getEventForOrganizer(1, 1);
        DetailedEventDTO expectedViewEventDTO = this.customDetailedEventDTO;

        Assertions.assertTrue(expectedViewEventDTO.equals(detailedEventDTO));
    }

    @Test
    public void createEvent() {
        this.initializeMocksAndCustomDTOs();

        DetailedEventDTO detailedEventDTO = this.customDetailedEventDTO;
        DetailedEventDTO resultEventDTO = this.eventService.createEvent(1, detailedEventDTO);

        Assertions.assertTrue(detailedEventDTO.equals(resultEventDTO));
    }

    @Test
    public void updateEvent() {
        this.initializeMocksAndCustomDTOs();

        DetailedEventDTO detailedEventDTO = this.customDetailedEventDTO;
        DetailedEventDTO resultEventDTO = this.eventService.updateEvent(1, 1, detailedEventDTO);

        Assertions.assertTrue(detailedEventDTO.equals(resultEventDTO));
    }

    @Test
    public void deleteEvent()
    {
        this.initializeMocksAndCustomDTOs();
        this.eventService.deleteEvent(1, 1);
    }

    public void getEventHeaderList()
    {

    }

    private void initializeMocksAndCustomDTOs() {
        this.initializeCustomDTOs();

        Mockito.when(this.eventRepositoryService.getEventAndHandleNotFound(Mockito.any(int.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(Mockito.any(Event.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.eventRepositoryService.updateEventAndHandleNotFound(Mockito.any(int.class), Mockito.any(Event.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.eventRepositoryService.getDTOfromDetailedEvent(Mockito.any(Event.class)))
                .thenReturn(this.customDetailedEventDTO);
        Mockito.when(this.eventRepositoryService.getDTOfromDetailedEvent(Mockito.any(Event.class)))
                .thenReturn(this.customDetailedEventDTO);
        Mockito.when(this.eventRepositoryService.getEventFromDetailedEventDTO(Mockito.any(DetailedEventDTO.class)))
                .thenReturn(this.customEvent);
    }
    private void initializeCustomDTOs() {
        this.initializeCustomEvent();
        this.initializeViewEventDTO();
        this.initializeDetailedEventDTO();
    }
    private void initializeCustomEvent() {
        this.customEvent = Event.builder()
                .id(1)
                .name("My Event")
                .eventLocation(new Location())
                .eventAds(new AdsOption())
                .build();
    }
    private void initializeViewEventDTO(){
        this.customViewEventDTO = new ViewEventDTO();
        this.customViewEventDTO.setId(1);
        this.customViewEventDTO.setName("My Event");
        this.customViewEventDTO.setEventLocation(new Location());
    }
    private void initializeDetailedEventDTO() {
        this.customDetailedEventDTO = new DetailedEventDTO();
        this.customDetailedEventDTO.id = 1;
        this.customDetailedEventDTO.name = "My Event";
        this.customDetailedEventDTO.eventLocation = new Location();
        this.customDetailedEventDTO.eventAds = new AdsOptionDTO();
    }
}

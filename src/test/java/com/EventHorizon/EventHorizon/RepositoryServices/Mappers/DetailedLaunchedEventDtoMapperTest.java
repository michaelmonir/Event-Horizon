package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DetailedLaunchedEventDtoMapperTest {
    @Mock
    private AdsOptionDtoMapper adsOptionDtoMapper;

    @InjectMocks
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;


    @Test
    public void testGetEventFromDetailedEventDTO() {

        DetailedLaunchedEventDto dto = new DetailedLaunchedEventDto();
        dto.setId(1);
        dto.setName("Test Event");
        dto.setDescription("Test Description");
        dto.setEventCategory("Test Category");
        dto.setEventAds(new AdsOptionDto());

        AdsOption adsOption = new AdsOption();
        Mockito.when(adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds())).thenReturn(adsOption);

        LaunchedEvent result = detailedLaunchedEventDtoMapper.getEventFromDetailedEventDTO(dto);

        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getName(), result.getName());
        Assertions.assertEquals(dto.getDescription(), result.getDescription());
        Assertions.assertEquals(dto.getEventCategory(), result.getEventCategory());
        Assertions.assertEquals(dto.getEventDate(), result.getEventDate());
        Assertions.assertEquals(dto.getLaunchedDate(), result.getLaunchedDate());
        Assertions.assertEquals(adsOption, result.getEventAds());
        Assertions.assertEquals(dto.getEventLocation(), result.getEventLocation());
    }
    @Test
    public void testGetDTOfromDetailedEvent() {

        Event event = new Event();
        event.setId(1);
        event.setName("Test Event");
        event.setDescription("Test Description");
        event.setEventCategory("Test Category");
        event.setEventAds(new AdsOption());
        Organizer organizer = new Organizer();
        organizer.setId(1);
        event.setEventOrganizer(organizer);
        Information information=new Information();
        information.setId(1);
        information.setUserName("ahmed");
        organizer.setInformation(information);
        LaunchedEvent launchedEvent= LaunchedEvent.builder().event(event).id(1).launchedDate(new Date()).build();
        DetailedLaunchedEventDto result = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(launchedEvent);

        Assertions.assertEquals(launchedEvent.getId(), result.getId());
        Assertions.assertEquals(launchedEvent.getName(), result.getName());
        Assertions.assertEquals(launchedEvent.getDescription(), result.getDescription());
        Assertions.assertEquals(launchedEvent.getEventCategory(), result.getEventCategory());
        Assertions.assertEquals(launchedEvent.getEventDate(), result.getEventDate());
        Assertions.assertEquals(launchedEvent.getEventAds().getId(), result.getEventAds().getId());
        Assertions.assertEquals(launchedEvent.getEventLocation(), result.getEventLocation());
        Assertions.assertEquals(launchedEvent.getLaunchedDate(), result.getLaunchedDate());
        Assertions.assertEquals(organizer.getId(), result.getEventOrganizer().getId());
        Assertions.assertEquals(organizer.getInformation().userName, result.getEventOrganizer().getName());
    }

}
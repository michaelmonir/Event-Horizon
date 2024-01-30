package com.EventHorizon.EventHorizon.Mappers.EventMappers.EventTypeMappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Mappers.AdsOptionDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedLaunchedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.SeatTypeListMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DetailedLaunchedEventDtoMapperTest {
    @Mock
    private AdsOptionDtoMapper adsOptionDtoMapper;
    @InjectMocks
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;
    @Mock
    private SeatTypeListMapper seatTypeListMapper;


    @Test
    public void testGetEventFromDetailedEventDTO() {

        DetailedLaunchedEventDto dto = new DetailedLaunchedEventDto();
        dto.setId(1);
        dto.setName("Test Event");
        dto.setDescription("Test Description");
        dto.setEventCategory("Test Category");
        dto.setEventAds(new AdsOptionDto());
        dto.setSeatTypes(new ArrayList<>());

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

        LaunchedEvent event = new LaunchedEvent();
        event.setId(1);
        event.setName("Test Event");
        event.setDescription("Test Description");
        event.setEventCategory("Test Category");
        event.setEventAds(new AdsOption());
        event.setSeatTypes(List.of(new SeatType("s1", 1, 1), new SeatType("s2", 2, 2)));
        event.setSeatTypes(new ArrayList<>());
        Organizer organizer = new Organizer();
        organizer.setId(1);
        organizer.setUserName("ahmed");
        event.setEventOrganizer(organizer);
        DetailedLaunchedEventDto result = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);
        Assertions.assertEquals(event.getId(), result.getId());
        Assertions.assertEquals(event.getName(), result.getName());
        Assertions.assertEquals(event.getDescription(), result.getDescription());
        Assertions.assertEquals(event.getEventCategory(), result.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), result.getEventDate());
        Assertions.assertEquals(event.getEventAds().getId(), result.getEventAds().getId());
        Assertions.assertEquals(event.getEventLocation(), result.getEventLocation());
        Assertions.assertEquals(event.getLaunchedDate(), result.getLaunchedDate());
        Assertions.assertEquals(organizer.getId(), result.getEventOrganizer().getId());
    }
}
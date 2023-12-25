package com.EventHorizon.EventHorizon.Mappers.EventMappers.EventTypeMappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Mappers.AdsOptionDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.SeatTypeListMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest
class DetailedDraftedEventDtoMapperTest {
    @Mock
    private AdsOptionDtoMapper adsOptionDtoMapper;
    @InjectMocks
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;
    @Mock
    private SeatTypeListMapper seatTypeListMapper;


    @Test
    public void testGetEventFromDetailedEventDTO() {

        DetailedDraftedEventDto dto = new DetailedDraftedEventDto();
        dto.setId(1);
        dto.setName("Test Event");
        dto.setDescription("Test Description");
        dto.setEventCategory("Test Category");
        dto.setEventAds(new AdsOptionDto());
        dto.setSeatTypes(new ArrayList<>());
        dto.setSeatTypes(new ArrayList<>());

        AdsOption adsOption = new AdsOption();
        Mockito.when(adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds())).thenReturn(adsOption);

        DraftedEvent result = detailedDraftedEventDtoMapper.getEventFromDetailedEventDTO(dto);

        Assertions.assertEquals(dto.getId(), result.getId());
        Assertions.assertEquals(dto.getName(), result.getName());
        Assertions.assertEquals(dto.getDescription(), result.getDescription());
        Assertions.assertEquals(dto.getEventCategory(), result.getEventCategory());
        Assertions.assertEquals(dto.getEventDate(), result.getEventDate());
        Assertions.assertEquals(adsOption, result.getEventAds());
        Assertions.assertEquals(dto.getEventLocation(), result.getEventLocation());
    }
    @Test
    public void testGetDTOfromDetailedEvent() {

        DraftedEvent event = new DraftedEvent();
        event.setId(1);
        event.setName("Test Event");
        event.setDescription("Test Description");
        event.setEventCategory("Test Category");
        event.setEventAds(new AdsOption());
        event.setSeatTypes(new ArrayList<>());

        Organizer organizer = new Organizer();
        organizer.setId(1);
        event.setEventOrganizer(organizer);
        Information information=new Information();
        information.setId(1);
        information.setUserName("ahmed");
        organizer.setInformation(information);

        DetailedDraftedEventDto result = detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);

        Assertions.assertEquals(event.getId(), result.getId());
        Assertions.assertEquals(event.getName(), result.getName());
        Assertions.assertEquals(event.getDescription(), result.getDescription());
        Assertions.assertEquals(event.getEventCategory(), result.getEventCategory());
        Assertions.assertEquals(event.getEventDate(), result.getEventDate());
        Assertions.assertEquals(event.getEventAds().getId(), result.getEventAds().getId());
        Assertions.assertEquals(event.getEventLocation(), result.getEventLocation());
        Assertions.assertEquals(organizer.getId(), result.getEventOrganizer().getId());
        Assertions.assertEquals(organizer.getInformation().userName, result.getEventOrganizer().getName());
    }

}
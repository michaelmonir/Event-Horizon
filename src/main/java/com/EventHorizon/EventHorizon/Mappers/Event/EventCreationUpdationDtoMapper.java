package com.EventHorizon.EventHorizon.Mappers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventCreationUpdationDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.SeatTypes.SeatTypeListMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.AdsOptionRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCreationUpdationDtoMapper
{
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private SeatTypeListMapper seatTypeListMapper;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;

    public DraftedEvent getEventFromDtoForCreating(EventCreationUpdationDto eventCreationUpdationDto) {
        return DraftedEvent.builder()
                .name(eventCreationUpdationDto.getName())
                .description(eventCreationUpdationDto.getDescription())
                .eventCategory(eventCreationUpdationDto.getEventCategory())
                .eventType(EventType.DRAFTEDEVENT)
                .eventDate(eventCreationUpdationDto.getEventDate())
                .eventAds(adsOptionRepositoryService.getById(eventCreationUpdationDto.getAdsOptionId()))
                .eventLocation(eventCreationUpdationDto.getEventLocation())
                .seatTypes(seatTypeListMapper.getSeatTypeListFromSeatTypeListDTO(eventCreationUpdationDto.getSeatTypes()))
                .build();
    }

    public void updateEventAttributesFromDto(Event event, EventCreationUpdationDto dto){
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setEventCategory(dto.getEventCategory());
        event.setEventDate(dto.getEventDate());
        event.setEventAds(adsOptionRepositoryService.getById(dto.getAdsOptionId()));
        event.setEventLocation(dto.getEventLocation());
        this.handleSeatTypesForDraftedEvent(event,dto);
    }

    private void handleSeatTypesForDraftedEvent(Event event, EventCreationUpdationDto dto){
        if (event.getEventType() == EventType.DRAFTEDEVENT){
            List<SeatType> list = seatTypeListMapper.getSeatTypeListFromSeatTypeListDTO(dto.getSeatTypes());
            event.setSeatTypes(list);
        }
    }
}
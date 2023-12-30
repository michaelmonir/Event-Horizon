package com.EventHorizon.EventHorizon.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventCreationDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.AdsOptionRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCreationDtoMapper
{
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private SeatTypeListMapper seatTypeListMapper;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;

    public DraftedEvent getEventForUpdating(EventCreationDto dto){
        DraftedEvent event = draftedEventRepositoryService.getByIdAndHandleNotFound(dto.getId());
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setEventCategory(dto.getEventCategory());
        event.setEventDate(dto.getEventDate());
        event.setEventAds(adsOptionRepositoryService.getById(dto.getAdsOptionId()));
        event.setEventLocation(dto.getEventLocation());
        event.setSeatTypes(seatTypeListMapper.getSeatTypeListFromSeatTypeListDTO(dto.getSeatTypes()));
        return event;
    }
}

package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedDraftedEventDtoMapper implements DetailedEventDtoMapper {
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public DraftedEvent getEventFromDetailedEventDTO(DetailedEventDto dto) {
        DetailedDraftedEventDto detailedDraftedEventDto=(DetailedDraftedEventDto)dto;
        Event event = Event.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .eventCategory(dto.getEventCategory())
                .eventDate(dto.getEventDate())
                .eventAds(this.adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds()))
                .eventLocation(dto.getEventLocation())
                .build();
        return DraftedEvent.builder().event(event).id(detailedDraftedEventDto.getId()).build();
    }

    public DetailedDraftedEventDto getDTOfromDetailedEvent(SuperEvent event) {
        DraftedEvent draftedEvent=(DraftedEvent)event;
        DetailedDraftedEventDto detailedDraftedEventDto = new DetailedDraftedEventDto(draftedEvent);
        return detailedDraftedEventDto;
    }
}

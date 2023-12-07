package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.AdsOptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LaunchedEventRepositoryService implements SuperEventRepositoryService  {
    @Autowired
    private LaunchedEventRepository launchedEventRepositry;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public LaunchedEvent getEventAndHandleNotFound(int id) {
        Optional<LaunchedEvent> optionalOldEvent=launchedEventRepositry.findById(id);
        if(optionalOldEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }

    public LaunchedEvent saveEventWhenCreatingAndHandleAlreadyExisting(SuperEvent event) {
        LaunchedEvent launchedEvent=(LaunchedEvent) event;
        if (launchedEvent.getId() != 0)
            throw new EventAlreadyExisting();

        launchedEventRepositry.save(launchedEvent);
        return launchedEvent;
    }

    public LaunchedEvent updateEventAndHandleNotFound(SuperEvent event) {
        LaunchedEvent newEvent=(LaunchedEvent) event;
        int id = newEvent.getId();
        this.getEventAndHandleNotFound(id);

        newEvent.setId(id);
        launchedEventRepositry.save(newEvent);
        return newEvent;
    }

    public void deleteEvent(int id) {
        Optional<LaunchedEvent> optionalOldEvent = launchedEventRepositry.findById(id);

        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();

        launchedEventRepositry.deleteById(id);
    }

    public LaunchedEvent setEventOrganizer(Organizer organizer, SuperEvent superEvent) {
        LaunchedEvent launchedEvent=(LaunchedEvent) superEvent;
        launchedEvent.setEventOrganizer(organizer);
        return launchedEvent;
    }
    public Event getEventFromSuperEvent(SuperEvent superEvent) {
        DraftedEvent draftedEvent = (DraftedEvent) superEvent;
        return draftedEvent.getEvent();
    }
    public ViewEventDto getViewEventDTO(int id) {
        Optional<LaunchedEvent> optionalOldEvent = launchedEventRepositry.findById(id);

        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();

        return new ViewEventDto(optionalOldEvent.get());
    }

    public EventHeaderDto getEventHeaderDto(int id) {
        Optional<LaunchedEvent> optionalOldEvent = launchedEventRepositry.findById(id);

        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();

        return new EventHeaderDto(optionalOldEvent.get());
    }

    public List<EventHeaderDto> getAllEventsHeaderDto(PageRequest pageRequest) {
        List<LaunchedEvent> events = launchedEventRepositry.findAll(pageRequest).getContent();
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (LaunchedEvent event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }
}

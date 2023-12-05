package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.AdsOptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EventRepositoryService {
    @Autowired
    private EventRepositry eventRepositry;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public Event getEventAndHandleNotFound(int id) {
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);
        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }

    public Event saveEventWhenCreatingAndHandleAlreadyExisting(Event event) {
        if (event.getId() != 0)
            throw new EventAlreadyExisting();

        eventRepositry.save(event);
        return event;
    }

    public Event updateEventAndHandleNotFound(Event newEvent) {
        int id = newEvent.getId();
        this.getEventAndHandleNotFound(id);

        newEvent.setId(id);
        eventRepositry.save(newEvent);
        return newEvent;
    }

    public void deleteEvent(int id) {
        Optional<Event> optionalOldEvent = eventRepositry.findById(id);

        if (!optionalOldEvent.isPresent())
            throw new EventNotFoundException();

        eventRepositry.deleteById(id);
    }

    public ViewEventDto getViewEventDTO(int id) {
        Optional<Event> optionalOldEvent = eventRepositry.findById(id);

        if (!optionalOldEvent.isPresent())
            throw new EventNotFoundException();

        return new ViewEventDto(optionalOldEvent.get());
    }

    public EventHeaderDto getEventHeaderDto(int id) {
        Optional<Event> optionalOldEvent = eventRepositry.findById(id);

        if (!optionalOldEvent.isPresent())
            throw new EventNotFoundException();

        return new EventHeaderDto(optionalOldEvent.get());
    }

    public List<EventHeaderDto> getAllEventsHeaderDto(PageRequest pageRequest) {
        List<Event> events = eventRepositry.findAll(pageRequest).getContent();
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (Event event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

}


package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.EventSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.EventSeatTypesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LaunchedEventRepositoryService implements SuperEventRepositoryService {
    @Autowired
    private LaunchedEventRepository launchedEventRepository;
    @Autowired
    private EventSeatTypesRepositoryService eventSeatTypesRepositoryService;
    @Autowired
    private ViewEventDtoMapper viewEventDtoMapper;
    @Autowired
    private EventSeatArchiveRepositoryService eventSeatArchiveRepositoryService;


    private static <T extends Event> Specification<T> castToLunchedEvents(Specification<? extends Event> obj) {
        // Warning: Unchecked cast
        return (Specification<T>) obj;
    }

    public LaunchedEvent getById(int id) {
        Optional<LaunchedEvent> optionalLaunchedEvent = launchedEventRepository.findById(id);
        if (optionalLaunchedEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalLaunchedEvent.get();
    }

    public LaunchedEvent saveWhenCreating(Event event) {
        LaunchedEvent launchedEvent = (LaunchedEvent) event;
        launchedEvent.setLaunchedDate(new Date());
        if (launchedEvent.getId() != 0)
            throw new EventAlreadyExisting();
        FutureEventWrapper eventWrapper = new FutureEventWrapper(launchedEvent);
        handleSeatArchivesAndSaveInRepository(eventWrapper);
        return launchedEvent;
    }

    public LaunchedEvent update(Event event) {
        LaunchedEvent newEvent = (LaunchedEvent) event;
        getById(newEvent.getId());
        int id = newEvent.getId();
        newEvent.setId(id);
        FutureEventWrapper eventWrapper = new FutureEventWrapper(newEvent);
        handleSeatArchivesAndSaveInRepository(eventWrapper);
        return newEvent;
    }

    public void delete(int id) {
        getById(id);
        launchedEventRepository.deleteById(id);
    }

    public ViewEventDto getViewEventDTO(int id) {
        LaunchedEvent launchedEvent = getById(id);
        return viewEventDtoMapper.getDTOfromViewEvent(launchedEvent);
    }

    public EventHeaderDto getEventHeaderDto(int id) {
        LaunchedEvent launchedEvent = getById(id);
        return new EventHeaderDto(launchedEvent);
    }

    public List<EventHeaderDto> getAllEventsHeaderDto(PageRequest pageRequest) {
        List<LaunchedEvent> events = launchedEventRepository.findAll(pageRequest).getContent();
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (LaunchedEvent event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

    public List<EventHeaderDto> getFilteredEventsHeaderDto(PageRequest pageWithRecords, Specification<Event> specification) {
        List<LaunchedEvent> events = (List<LaunchedEvent>) getAllEvents(specification, pageWithRecords);
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (LaunchedEvent event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

    private void handleSeatArchivesAndSaveInRepository(FutureEventWrapper futureEventWrapper) {
        eventSeatTypesRepositoryService.setEventForItsSeatTypes(futureEventWrapper.getLaunchedEvent());
        launchedEventRepository.save(futureEventWrapper.getLaunchedEvent());
        eventSeatArchiveRepositoryService.setAndSaveSeatArchivesForEvent(futureEventWrapper.getLaunchedEvent());
    }

    public List<? extends Event> getAllEvents(Specification<Event> specification) {
        return launchedEventRepository.findAll(castToLunchedEvents(specification));
    }

    public List<? extends Event> getAllEvents(Specification<Event> specification, PageRequest pageRequest) {
        return launchedEventRepository.findAll(castToLunchedEvents(specification), pageRequest).getContent();
    }

    public List<LaunchedEvent> getAllEvents() {
        return launchedEventRepository.findAll();
    }

    public List<SeatType> getSeatTypeById(int id) {
        LaunchedEvent launchedEvent = this.getById(id);
        return launchedEvent.getSeatTypes();
    }
}


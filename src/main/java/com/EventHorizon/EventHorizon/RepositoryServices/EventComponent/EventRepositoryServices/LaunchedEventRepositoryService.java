package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidEventIdException;
import com.EventHorizon.EventHorizon.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.EventSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.EventSeatTypesRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
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
    @Autowired
    private EventRepository eventRepository;
    private static <T extends Event> Specification<T> castToLunchedEvents(Specification<? extends Event> obj) {
        // Warning: Unchecked cast
        return (Specification<T>) obj;
    }

    public LaunchedEvent getByIdAndHandleNotFound(int id) {
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

    public LaunchedEvent saveWhenLaunching(Event event) {
        LaunchedEvent launchedEvent = (LaunchedEvent) event;
        launchedEvent.setLaunchedDate(DateFunctions.getCurrentDate());
        if (launchedEvent.getId() == 0)
            throw new InvalidEventIdException();
        FutureEventWrapper eventWrapper = new FutureEventWrapper(launchedEvent);
        handleSeatArchivesAndSaveInRepository(eventWrapper);
        return launchedEvent;
    }

    public LaunchedEvent update(Event event) {
        LaunchedEvent newEvent = (LaunchedEvent) event;
        getByIdAndHandleNotFound(newEvent.getId());
        int id = newEvent.getId();
        newEvent.setId(id);
        FutureEventWrapper eventWrapper = new FutureEventWrapper(newEvent);
//        handleSeatArchivesAndSaveInRepository(eventWrapper);
        handleSeatArchivesAndSaveInRepository(eventWrapper);
        return newEvent;
    }

    public void delete(int id) {
        getByIdAndHandleNotFound(id);
        launchedEventRepository.deleteById(id);
    }

    public ViewEventDto getViewEventDTO(int id) {
        LaunchedEvent launchedEvent = getByIdAndHandleNotFound(id);
        return viewEventDtoMapper.getDTOfromViewEvent(launchedEvent);
    }

    public EventHeaderDto getEventHeaderDto(int id) {
        LaunchedEvent launchedEvent = getByIdAndHandleNotFound(id);
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
        List<Event> events = (List<Event>) getAllEvents(specification, pageWithRecords);
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (Event event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

    private void handleSeatArchivesAndSaveInRepository(FutureEventWrapper futureEventWrapper) {
        LaunchedEvent event = futureEventWrapper.getLaunchedEvent();
        event.setEventType(EventType.LAUNCHEDEVENT);
        eventSeatTypesRepositoryService.setEventForItsSeatTypes(event);
        launchedEventRepository.save(event);
        eventSeatArchiveRepositoryService.setAndSaveSeatArchivesForEvent(event);
    }

    // shouldn't update the seat types of the event
//    private void handleDateAndSaveInRepository(FutureEventWrapper futureEventWrapper) {
//        LaunchedEvent event = futureEventWrapper.getLaunchedEvent();
//        event.setEventType(EventType.LAUNCHEDEVENT);
////        eventSeatTypesRepositoryService.setEventForItsSeatTypes(event);
//        launchedEventRepository.save(event);
////        eventSeatArchiveRepositoryService.setAndSaveSeatArchivesForEvent(event);
//    }

    public List<? extends Event> getAllEvents(Specification<Event> specification) {
        return launchedEventRepository.findAll(castToLunchedEvents(specification));
    }

    public List<? extends Event> getAllEvents(Specification<Event> specification, PageRequest pageRequest) {
        return eventRepository.findAll(specification, pageRequest).getContent();
    }

    public List<LaunchedEvent> getAllEvents() {
        return launchedEventRepository.findAll();
    }

    public List<SeatType> getSeatTypeById(int id) {
        LaunchedEvent launchedEvent = this.getByIdAndHandleNotFound(id);
        return launchedEvent.getSeatTypes();
    }
}


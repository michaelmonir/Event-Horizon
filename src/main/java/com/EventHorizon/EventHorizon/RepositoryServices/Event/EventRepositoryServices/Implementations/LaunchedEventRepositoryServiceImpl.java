package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.Event.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Entities.Event.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.Repository.Views.ClientGoingViewRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaunchedEventRepositoryServiceImpl implements SuperEventRepositoryService, LaunchedEventRepositoryService {
    @Autowired
    private CommonEventRepositoryService commonEventRepositoryService;
    @Autowired
    private ClientGoingViewRepository clientGoingViewRepository;
    @Autowired
    private LaunchedEventRepository launchedEventRepository;
    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public LaunchedEvent saveWhenLaunching(LaunchedEvent event) {
        event.setLaunchedDate(DateFunctions.getCurrentDate());
        if (event.getId() != 0)
            throw new EventAlreadyExisting();
        new FutureEventWrapper(event);
        commonEventRepositoryService.save(event);
        return event;
    }

    @Override
    public void delete(int id) {
        this.checkAndHandleWrongType(id);
        eventRepository.deleteById(id);
    }

    @Override
    public LaunchedEvent update(Event newEvent) {
        this.checkAndHandleWrongType(newEvent.getId());
        new FutureEventWrapper((LaunchedEvent) newEvent);
        commonEventRepositoryService.save(newEvent);
        return (LaunchedEvent) newEvent;
    }

    public List<EventHeaderDto> getAllEventsHeaderDto(PageRequest pageRequest) {
        List<LaunchedEvent> events = launchedEventRepository.findAll(pageRequest).getContent();
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (LaunchedEvent event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

    public List<SeatType> getSeatTypeById(int id) {
        LaunchedEvent launchedEvent = this.getById(id);
        return launchedEvent.getSeatTypes();
    }

    public List<EventHeaderDto> getFilteredEventsHeaderDto(PageRequest pageWithRecords, Specification<Event> specification) {
        List<Event> events = (List<Event>) this.getAllEvents(specification, pageWithRecords);
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (Event event : events) {
            eventHeaderDtos.add(new EventHeaderDto(event));
        }
        return eventHeaderDtos;
    }

    private List<? extends Event> getAllEvents(Specification<Event> specification, PageRequest pageRequest) {
        return eventRepository.findAll(specification, pageRequest).getContent();
    }

    public List<EventHeaderDto> getFilteredEventsHeaderDtoFromClientGoingView(PageRequest pageWithRecords, Specification<ClientGoingView> specification) {
        List<ClientGoingView> clientGoingViews =  clientGoingViewRepository.findAll(specification, pageWithRecords).getContent();
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (ClientGoingView clientGoingView : clientGoingViews) {
            eventHeaderDtos.add(new EventHeaderDto(clientGoingView.getEvent()));
        }
        return eventHeaderDtos;
    }

    @Override
    public LaunchedEvent getById(int id) {
        Event event = commonEventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
        return (LaunchedEvent) event;
    }
    private void checkAndHandleWrongType(int id) {
        Event event = commonEventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
    }
}


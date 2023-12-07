package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NewEventDateIsBeforeNow;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.UpdatePastEvent;
import com.EventHorizon.EventHorizon.Repository.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.EventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.EventWrapperFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FinishedEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FutureEventWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LaunchedEventRepositoryService implements SuperEventRepositoryService {
    @Autowired
    private LaunchedEventRepository launchedEventRepository;
    @Autowired
    private EventWrapperFactory eventWrapperFactory;

    public LaunchedEvent getEventAndHandleNotFound(int id) {
        Optional<LaunchedEvent> optionalLaunchedEvent = launchedEventRepository.findById(id);
        if (optionalLaunchedEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalLaunchedEvent.get();
    }

    public LaunchedEvent saveEventWhenCreatingAndHandleAlreadyExisting(Event event) {
        LaunchedEvent launchedEvent = (LaunchedEvent) event;
        if (launchedEvent.getId() != 0)
            throw new EventAlreadyExisting();
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(launchedEvent);
        checkEventDate(eventWrapper);
        saveEvent((FutureEventWrapper) eventWrapper);
        return launchedEvent;
    }

    public LaunchedEvent updateEventAndHandleNotFound(Event event) {
        LaunchedEvent newEvent = (LaunchedEvent) event;
        int id = newEvent.getId();
        getEventAndHandleNotFoundAndCheckEventDate(id);
        newEvent.setId(id);
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(newEvent);
        checkEventDate(eventWrapper);
        saveEvent((FutureEventWrapper) eventWrapper);
        return newEvent;
    }

    public void deleteEvent(int id) {
        getEventAndHandleNotFound(id);
        launchedEventRepository.deleteById(id);
    }


    public ViewEventDto getViewEventDTO(int id) {
        LaunchedEvent launchedEvent = getEventAndHandleNotFound(id);
        return new ViewEventDto(launchedEvent);
    }

    public EventHeaderDto getEventHeaderDto(int id) {
        LaunchedEvent launchedEvent = getEventAndHandleNotFound(id);
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

    private void checkEventDate(EventWrapper eventWrapper) {
        if (eventWrapper instanceof FinishedEventWrapper) {
            throw new NewEventDateIsBeforeNow();
        }
    }

    private void getEventAndHandleNotFoundAndCheckEventDate(int id) {
        LaunchedEvent launchedEvent = getEventAndHandleNotFound(id);
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(launchedEvent);
        if (eventWrapper instanceof FinishedEventWrapper) {
            throw new UpdatePastEvent();
        }
    }

    private void saveEvent(FutureEventWrapper futureEventWrapper) {
        launchedEventRepository.save(futureEventWrapper.getLaunchedEvent());
    }
}


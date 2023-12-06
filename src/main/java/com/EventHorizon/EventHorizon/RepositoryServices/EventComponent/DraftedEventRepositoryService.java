package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.DraftedEventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.AdsOptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DraftedEventRepositoryService  {

    @Autowired
    private DraftedEventRepository draftedEventRepository;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public DraftedEvent getEventAndHandleNotFound(int id) {
        Optional<DraftedEvent> optionalOldEvent=draftedEventRepository.findById(id);
        if(optionalOldEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }

    public DraftedEvent saveEventWhenCreatingAndHandleAlreadyExisting(DraftedEvent launchedEvent) {
        if (launchedEvent.getId() != 0)
            throw new EventAlreadyExisting();

        draftedEventRepository.save(launchedEvent);
        return launchedEvent;
    }

    public DraftedEvent updateEventAndHandleNotFound(DraftedEvent newEvent) {
        int id = newEvent.getId();
        this.getEventAndHandleNotFound(id);

        newEvent.setId(id);
        draftedEventRepository.save(newEvent);
        return newEvent;
    }

    public void deleteEvent(int id) {
        Optional<DraftedEvent> optionalOldEvent = draftedEventRepository.findById(id);

        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();

        draftedEventRepository.deleteById(id);
    }

}

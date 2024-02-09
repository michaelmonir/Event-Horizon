package com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter;

import com.EventHorizon.EventHorizon.Exceptions.Filter.NotFoundViewRepositoryService;
import com.EventHorizon.EventHorizon.Repository.Event.DraftedEventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Repository.Views.ClientGoingViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewRepositoryServiceFactory {
    @Autowired
    private LaunchedEventRepository launchedEventRepository;
    @Autowired
    private DraftedEventRepository draftedEventRepository;
    @Autowired
    private ClientGoingViewRepository clientGoingViewRepository;

    public ViewEventInterface<? extends HeaderInterface> getViewRepositoryService(EventViewType viewType) {
        return switch (viewType) {
            case DRAFTED -> draftedEventRepository;
            case LAUNCHED -> launchedEventRepository;
            case GOING -> clientGoingViewRepository;
            default -> throw new NotFoundViewRepositoryService();
        };
    }
}

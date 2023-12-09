package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.AvailableTicketsIsLessThanRequiredToBuy;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.BuyedTicketsIslessThanRequiredToRefund;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventIsFinished;
import com.EventHorizon.EventHorizon.Mock.BuyableSeatInventory;
import com.EventHorizon.EventHorizon.Mock.BuyableSeatInventoryRepositoryService;
import com.EventHorizon.EventHorizon.Mock.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Mock.BuyedTicketCollectionRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.EventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.EventWrapperFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FinishedEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private EventWrapperFactory eventWrapperFactory;
    @Autowired
    private BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;
    @Autowired
    private BuyableSeatInventoryRepositoryService buyableSeatInventoryRepositoryService;

    public void buyTicket(int clientId, int launchedEventId, int seatTypeId, int numOfTickets) {
        validateEventIsLaunchedAndIsFuture(launchedEventId);
        validateNumberOfTicketsWhileBuying(numOfTickets, seatTypeId);
        buyedTicketCollectionRepositoryService.saveTickets(clientId, seatTypeId, numOfTickets);
    }

    public void refundTicket(int clientId, int launchedEventId, int seatTypeId, int numOfTickets) {
        validateEventIsLaunchedAndIsFuture(launchedEventId);
        validateNumberOfTicketsWhileRefunding(numOfTickets, clientId, seatTypeId);
        buyableSeatInventoryRepositoryService.saveSeatInventory(seatTypeId, numOfTickets);
    }

    public void validateEventIsLaunchedAndIsFuture(int launchedEventId) {
        LaunchedEvent launchedEvent = launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId);
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(launchedEvent);
        if (eventWrapper instanceof FinishedEventWrapper) {
            throw new EventIsFinished();
        }
    }

    public void validateNumberOfTicketsWhileBuying(int numOfTickets, int seatTypeId) {
        BuyableSeatInventory buyableSeatInventory = buyableSeatInventoryRepositoryService.find(seatTypeId);
        int noOfAvailableTickets = buyableSeatInventory.getAvailableTickets();
        if (numOfTickets > noOfAvailableTickets)
            throw new AvailableTicketsIsLessThanRequiredToBuy();
        buyableSeatInventory.setNoOfAvailableTickets(noOfAvailableTickets - numOfTickets);
        buyableSeatInventoryRepositoryService.update(buyableSeatInventory);
    }

    public void validateNumberOfTicketsWhileRefunding(int numOfTickets, int clientId, int seatTypeId) {
        BuyedTicketCollection buyedTicketCollection = buyedTicketCollectionRepositoryService.find(clientId, seatTypeId);
        int numOfBuyedTickets = buyedTicketCollection.getNumberOfTickets();
        if (numOfTickets > numOfBuyedTickets)
            throw new BuyedTicketsIslessThanRequiredToRefund();
        buyedTicketCollection.setNumberOfTickets(numOfBuyedTickets - numOfTickets);
        buyedTicketCollectionRepositoryService.update(buyedTicketCollection);
    }
}

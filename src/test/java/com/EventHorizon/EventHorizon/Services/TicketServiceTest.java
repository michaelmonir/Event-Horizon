package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.AvailableTicketsIsLessThanRequiredToBuy;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.BuyedTicketsIslessThanRequiredToRefund;
import com.EventHorizon.EventHorizon.Mock.BuyableSeatInventory;
import com.EventHorizon.EventHorizon.Mock.BuyableSeatInventoryRepositoryService;
import com.EventHorizon.EventHorizon.Mock.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Mock.BuyedTicketCollectionRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.EventWrapperFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TicketServiceTest {
    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;

    @Mock
    private EventWrapperFactory eventWrapperFactory;

    @Mock
    private BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;

    @Mock
    private BuyableSeatInventoryRepositoryService buyableSeatInventoryRepositoryService;

    @InjectMocks
    private TicketService ticketService;


    @Test
    public void buyTicketValidRequestSuccess() {
        int clientId = 1;
        int launchedEventId = 2;
        int seatTypeId = 3;
        int numOfTickets = 4;
        LaunchedEvent launchedEvent = new LaunchedEvent();
        BuyableSeatInventory buyableSeatInventory = new BuyableSeatInventory();
        buyableSeatInventory.setNoOfAvailableTickets(10);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
        when(eventWrapperFactory.getEventWrapper(launchedEvent)).thenReturn(mock(FutureEventWrapper.class));
        when(buyableSeatInventoryRepositoryService.find(seatTypeId)).thenReturn(buyableSeatInventory);
        ticketService.buyTicket(clientId, launchedEventId, seatTypeId, numOfTickets);
        verify(buyedTicketCollectionRepositoryService).saveTickets(clientId, seatTypeId, numOfTickets);
    }

    @Test
    public void buyTicketInsufficientAvailableTicketsThrowsException() {
        int clientId = 1;
        int launchedEventId = 2;
        int seatTypeId = 3;
        int numOfTickets = 15;
        LaunchedEvent launchedEvent = new LaunchedEvent();
        BuyableSeatInventory buyableSeatInventory = new BuyableSeatInventory();
        buyableSeatInventory.setNoOfAvailableTickets(10);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
        when(eventWrapperFactory.getEventWrapper(launchedEvent)).thenReturn(mock(FutureEventWrapper.class));
        when(buyableSeatInventoryRepositoryService.find(seatTypeId)).thenReturn(buyableSeatInventory);
        assertThrows(AvailableTicketsIsLessThanRequiredToBuy.class,
                () -> ticketService.buyTicket(clientId, launchedEventId, seatTypeId, numOfTickets));
    }
    @Test
    public void refundTicketValidRequestSuccess() {
        int clientId = 1;
        int launchedEventId = 2;
        int seatTypeId = 3;
        int numOfTickets = 4;
        LaunchedEvent launchedEvent = new LaunchedEvent();
        BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection();
        buyedTicketCollection.setNumberOfTickets(10);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
        when(eventWrapperFactory.getEventWrapper(launchedEvent)).thenReturn(mock(FutureEventWrapper.class));
        when(buyedTicketCollectionRepositoryService.find(clientId, seatTypeId)).thenReturn(buyedTicketCollection);
        ticketService.refundTicket(clientId, launchedEventId, seatTypeId, numOfTickets);
        verify(buyableSeatInventoryRepositoryService).saveSeatInventory(seatTypeId, numOfTickets);
    }

    @Test
    public void refundTicketInsufficientBuyedTicketsThrowsException() {
        int clientId = 1;
        int launchedEventId = 2;
        int seatTypeId = 3;
        int numOfTickets = 15;
        LaunchedEvent launchedEvent = new LaunchedEvent();
        BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection();
        buyedTicketCollection.setNumberOfTickets(10);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
        when(eventWrapperFactory.getEventWrapper(launchedEvent)).thenReturn(mock(FutureEventWrapper.class));
        when(buyedTicketCollectionRepositoryService.find(clientId, seatTypeId)).thenReturn(buyedTicketCollection);
        assertThrows(BuyedTicketsIslessThanRequiredToRefund.class,
                () -> ticketService.refundTicket(clientId, launchedEventId, seatTypeId, numOfTickets));
    }

}
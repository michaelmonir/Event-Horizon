package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Repository.ClientRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TicketServiceTest {
    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Mock
    private BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;
    @Mock
    private OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;
    @InjectMocks
    private TicketService ticketService;

    private Client customclient;
    private SeatType customseatType;
    private SeatType custom;

    @Autowired
    private ClientRepository clientRepository;


    public void buyTicketValidRequestSuccess() {

    }


    private void initializeCustomObjects() {
//        this.client = Client.builder().id(1).build();
//        this.seatType = new SeatType("a", 1);

    }


//    @Test
//    public void buyTicketValidRequestSuccess() {
//        int clientId = 1;
//        int launchedEventId = 2;
//        int seatTypeId = 3;
//        int numOfTickets = 4;
//        LaunchedEvent launchedEvent = new LaunchedEvent();
//        launchedEvent.setEventDate(new Date(System.currentTimeMillis() + 100000));
//        SeatType seatType = new SeatType("a", 1);
//        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 10, 10);
//
//        BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection(new Client(), seatType, numOfTickets);
//
//        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
//        when(organizerSeatArchiveRepositoryService
//                .getOrganizerArchiveByClientIdAndSeatTypeId(seatTypeId)).thenReturn(organizerSeatArchive);
//        when(buyedTicketCollectionRepositoryService
//                .getArchiveBySeatTypeIdAndClientId(any(int.class), any(int.class))).thenReturn(buyedTicketCollection);
//
//
//        buyedTicketCollection.setNumberOfTickets(buyedTicketCollection.getNumberOfTickets() + numOfTickets);
//
//        buyedTicketCollection.setNumberOfTickets(buyedTicketCollection.getNumberOfTickets() + numOfTickets);
//        BuyedTicketCollection modifiedTicketCollection
//                =  new BuyedTicketCollection(new Client(), seatType, buyedTicketCollection.getNumberOfTickets()+numOfTickets);
//
//        verify(buyedTicketCollectionRepositoryService).saveBuyedTicketCollection(modifiedTicketCollection);
//
//        ticketService.buyTicket(clientId, launchedEventId, seatTypeId, numOfTickets);
//    }

//    @Test
//    public void buyTicketInsufficientAvailableTicketsThrowsException() {
//        int clientId = 1;
//        int launchedEventId = 2;
//        int seatTypeId = 3;
//        int numOfTickets = 15;
//        LaunchedEvent launchedEvent = new LaunchedEvent();
//        launchedEvent.setEventDate(new Date(System.currentTimeMillis() + 100000));
//        BuyableSeatInventory buyableSeatInventory = new BuyableSeatInventory();
//        buyableSeatInventory.setNoOfAvailableTickets(10);
//        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
//        when(organizerSeatArchiveRepositoryService.find(seatTypeId)).thenReturn(buyableSeatInventory);
//        assertThrows(AvailableTicketsIsLessThanRequiredToBuy.class,
//                () -> ticketService.buyTicket(clientId, launchedEventId, seatTypeId, numOfTickets));
//    }
//    @Test
//    public void refundTicketValidRequestSuccess() {
//        int clientId = 1;
//        int launchedEventId = 2;
//        int seatTypeId = 3;
//        int numOfTickets = 4;
//        LaunchedEvent launchedEvent = new LaunchedEvent();
//        launchedEvent.setEventDate(new Date(System.currentTimeMillis() + 100000));
//        BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection();
//        buyedTicketCollection.setNumberOfTickets(10);
//        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
//        when(buyedTicketCollectionRepositoryService.find(clientId, seatTypeId)).thenReturn(buyedTicketCollection);
//        ticketService.refundTicket(clientId, launchedEventId, seatTypeId, numOfTickets);
//        verify(organizerSeatArchiveRepositoryService).saveSeatInventory(seatTypeId, numOfTickets);
//    }
//
//    @Test
//    public void refundTicketInsufficientBuyedTicketsThrowsException() {
//        int clientId = 1;
//        int launchedEventId = 2;
//        int seatTypeId = 3;
//        int numOfTickets = 15;
//        LaunchedEvent launchedEvent = new LaunchedEvent();
//        launchedEvent.setEventDate(new Date(System.currentTimeMillis() + 100000));
//        BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection();
//        buyedTicketCollection.setNumberOfTickets(10);
//        when(launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId)).thenReturn(launchedEvent);
//        when(buyedTicketCollectionRepositoryService.find(clientId, seatTypeId)).thenReturn(buyedTicketCollection);
//        assertThrows(BuyedTicketsIslessThanRequiredToRefund.class,
//                () -> ticketService.refundTicket(clientId, launchedEventId, seatTypeId, numOfTickets));
//    }

}
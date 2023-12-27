package com.EventHorizon.EventHorizon.Service.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.BuyedTicketsIslessThanRequiredToRefund;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketOperationsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketOperationServiceTest
{
    @InjectMocks
    TicketOperationsService ticketOperationsService;
    @Mock
    BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;

    private SeatType customSeatType;
    private BuyedTicketCollection customCollectionWithTickets;
    private BuyedTicketCollection customCollectionWithoutTickets;
    private Client customClient;

    @Test
    public void removeTicketsSuccessful(){
        this.initializeCustomObjects();
        when(buyedTicketCollectionRepositoryService.getAndHandleLazyInitialization(any(SeatType.class), any(Client.class)))
                .thenReturn(customCollectionWithTickets);

        this.ticketOperationsService.removeTickets(this.customSeatType, this.customClient, 1);

        verify(this.buyedTicketCollectionRepositoryService, Mockito.times(1))
                .save(this.customCollectionWithoutTickets);
    }

    @Test
    public void removeTicketsMoreTicketsThatItHas(){
        this.initializeCustomObjects();
        when(buyedTicketCollectionRepositoryService.getAndHandleLazyInitialization(any(SeatType.class), any(Client.class)))
                .thenReturn(customCollectionWithTickets);

        Assertions.assertThrows(BuyedTicketsIslessThanRequiredToRefund.class, () ->
                this.ticketOperationsService.removeTickets(this.customSeatType, this.customClient, 2));
    }

    @Test
    public void addTicketsSuccessful(){
        this.initializeCustomObjects();
        when(buyedTicketCollectionRepositoryService.getAndHandleLazyInitialization(any(SeatType.class), any(Client.class)))
                .thenReturn(customCollectionWithoutTickets);

        this.ticketOperationsService.addTickets(this.customSeatType, this.customClient, 1);

        verify(this.buyedTicketCollectionRepositoryService, Mockito.times(1))
                .save(this.customCollectionWithTickets);
    }

    private void initializeCustomObjects(){
        this.customSeatType = new SeatType("a", 1, 1);
        this.customClient = Client.builder().id(1).build();
        this.customCollectionWithTickets = new BuyedTicketCollection(this.customClient, this.customSeatType, 1);
        this.customCollectionWithoutTickets = new BuyedTicketCollection(this.customClient, this.customSeatType, 0);
    }
}

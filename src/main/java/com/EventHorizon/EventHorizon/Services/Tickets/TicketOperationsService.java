package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.BuyedTicketsIslessThanRequiredToRefund;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketOperationsService
{
    @Autowired
    BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;

    public void addTickets(SeatType seatType, Client client, int numOfTickets) {
        BuyedTicketCollection buyedTicketCollection =
                this.buyedTicketCollectionRepositoryService.getAndHandleLazyInitialization(seatType, client);

        int oldNumOfTickets = buyedTicketCollection.getNumberOfTickets();

        buyedTicketCollection.setNumberOfTickets(oldNumOfTickets + numOfTickets);
        this.buyedTicketCollectionRepositoryService.save(buyedTicketCollection);
    }

    public void removeTickets(SeatType seatType, Client client, int numOfTickets) {
        BuyedTicketCollection buyedTicketCollection =
                this.buyedTicketCollectionRepositoryService.getAndHandleLazyInitialization(seatType, client);

        int oldNumOfTickets = buyedTicketCollection.getNumberOfTickets();

        if (oldNumOfTickets < numOfTickets)
            throw new BuyedTicketsIslessThanRequiredToRefund();

        buyedTicketCollection.setNumberOfTickets(oldNumOfTickets - numOfTickets);
        this.buyedTicketCollectionRepositoryService.save(buyedTicketCollection);
    }
}

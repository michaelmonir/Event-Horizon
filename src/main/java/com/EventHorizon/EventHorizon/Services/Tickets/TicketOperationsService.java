package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.BuyedTicketsIslessThanRequiredToRefund;
import com.EventHorizon.EventHorizon.Repository.Tickets.BuyedTicketCollectionRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketOperationsService
{
    @Autowired
    BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;

    public void addTickets(int seatTypeId, int clientId, int numOfTickets) {
        BuyedTicketCollection buyedTicketCollection =
                this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(seatTypeId, clientId);

        int oldNumOfTickets = buyedTicketCollection.getNumberOfTickets();

        BuyedTicketCollection newone
                = new BuyedTicketCollection(buyedTicketCollection.getClient(), buyedTicketCollection.getSeatType(), oldNumOfTickets + numOfTickets);
//        buyedTicketCollection.setNumberOfTickets(oldNumOfTickets + numOfTickets);
        this.buyedTicketCollectionRepositoryService.save(newone);
    }

    public void removeTickets(int seatTypeId, int clientId, int numOfTickets) {
        BuyedTicketCollection buyedTicketCollection =
                this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(seatTypeId, clientId);

        int oldNumOfTickets = buyedTicketCollection.getNumberOfTickets();

        if (oldNumOfTickets < numOfTickets)
            throw new BuyedTicketsIslessThanRequiredToRefund();

        buyedTicketCollection.setNumberOfTickets(oldNumOfTickets - numOfTickets);
        this.buyedTicketCollectionRepositoryService.save(buyedTicketCollection);
    }
}

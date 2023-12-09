package com.EventHorizon.EventHorizon.RepositoryServices.Tickets;

import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Exceptions.Tickets.BuyedTicketCollectionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.Tickets.BuyedTicketCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyedTicketCollectionRepositoryService
{
    @Autowired
    BuyedTicketCollectionRepository buyedTicketCollectionRepository;

    public BuyedTicketCollection getOrganizerArchiveByClientIdAndSeatTypeId(int seatTypeId, int clientId) {
        Optional<BuyedTicketCollection> optionalBuyedTicketCollection
                = this.buyedTicketCollectionRepository.findByClientIdAndSeatTypeId(clientId, seatTypeId);
        if (!optionalBuyedTicketCollection.isPresent())
            throw new BuyedTicketCollectionNotFoundException();
        return optionalBuyedTicketCollection.get();
    }

    public void saveBuyedTicketCollection(BuyedTicketCollection buyedTicketCollection) {
        this.buyedTicketCollectionRepository.save(buyedTicketCollection);
    }
}
